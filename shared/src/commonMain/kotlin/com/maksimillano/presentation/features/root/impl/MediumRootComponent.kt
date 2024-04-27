package com.maksimillano.presentation.features.root.impl

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.base.ContextWrapperImpl
import com.maksimillano.presentation.features.FeatureConfig
import com.maksimillano.presentation.features.navigation.NavigationComponentImpl
import com.maksimillano.presentation.base.AppNavigator
import com.maksimillano.presentation.base.NavigationOwner
import com.maksimillano.presentation.base.NavigationOwnerImpl
import com.maksimillano.presentation.features.root.RootComponentConfig
import com.maksimillano.presentation.util.createContextWrapper

class MediumRootComponent(
    private val config: RootComponentConfig
) : AppNavigator,
    RenderComponent,
    ComponentContext by config.context {

    private val navigationOwner: NavigationOwner = NavigationOwnerImpl(this)
    private val navigationSmallComponent = NavigationComponentImpl(
        createContextWrapper("NavBar", navigationOwner)
    )

    private val navigation = StackNavigation<FeatureConfig>()
    private val stack: Value<ChildStack<FeatureConfig, RenderComponent>> = childStack(
        source = navigation,
        initialStack = { listOf(config.initialFeatureConfig) },
        childFactory = ::createComponent
    )

    private fun createComponent(featureConfig: FeatureConfig, componentContext: ComponentContext): RenderComponent {
        val contextWrapper = ContextWrapperImpl(componentContext, navigationOwner)
        return config.componentFabric.createComponent(featureConfig, contextWrapper)
    }

    @Composable
    override fun render() {
        val stack by stack.subscribeAsState()
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxHeight()
                    .width(50.dp)
            ) {
                navigationSmallComponent.render()
            }
            Children(
                stack = stack,
                modifier = Modifier.fillMaxSize()
            ) { child ->
                child.instance.render()
            }
        }
    }

    override fun onNavigate(featureConfig: FeatureConfig) {
        navigation.push(featureConfig)
    }

    override fun onButtonBack() {
        navigation.pop()
    }
}