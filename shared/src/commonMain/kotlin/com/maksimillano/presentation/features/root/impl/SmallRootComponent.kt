package com.maksimillano.presentation.features.root.impl

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.base.ContextWrapperImpl
import com.maksimillano.presentation.features.FeatureConfig
import com.maksimillano.presentation.base.AppNavigator
import com.maksimillano.presentation.base.NavigationOwner
import com.maksimillano.presentation.base.NavigationOwnerImpl
import com.maksimillano.presentation.features.root.RootComponentConfig

class SmallRootComponent(
    private val config: RootComponentConfig
) : AppNavigator,
    RenderComponent,
    ComponentContext by config.context {

    private val navigationOwner: NavigationOwner = NavigationOwnerImpl(this)
    private val navigation = StackNavigation<FeatureConfig>()
    private val stack: Value<ChildStack<FeatureConfig, RenderComponent>> = childStack(
        source = navigation,
        initialStack = { listOf(config.initialFeatureConfig) },
        childFactory = ::createComponent,
        handleBackButton = true
    )

    private fun createComponent(featureConfig: FeatureConfig, componentContext: ComponentContext): RenderComponent {
        val contextWrapper = ContextWrapperImpl(componentContext, navigationOwner)
        return config.componentFabric.createComponent(featureConfig, contextWrapper)
    }

    @Composable
    override fun render() {
        val stack by stack.subscribeAsState()
        Children(
            stack = stack,
            animation = stackAnimation(
                slide(animationSpec = tween())
            )
        ) { child ->
            child.instance.render()
        }
    }

    override fun onNavigate(featureConfig: FeatureConfig) {
        navigation.push(featureConfig)
    }

    override fun onButtonBack() {
        navigation.pop()
    }
}