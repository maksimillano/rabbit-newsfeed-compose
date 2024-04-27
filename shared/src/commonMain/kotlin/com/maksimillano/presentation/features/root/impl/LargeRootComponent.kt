package com.maksimillano.presentation.features.root.impl

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.maksimillano.presentation.base.NavigationOwnerImpl
import com.maksimillano.presentation.features.root.RootComponentConfig

class LargeRootComponent(
    private val config: RootComponentConfig
) : RenderComponent,
    ComponentContext by config.context {

    private val leftNavigator = LeftNavigator()
    private val leftNavigation = StackNavigation<FeatureConfig>()
    private val leftStack: Value<ChildStack<FeatureConfig, RenderComponent>> = childStack(
        key = "LeftStack",
        source = leftNavigation,
        initialStack = { listOf(FeatureConfig.Navigation) },
        childFactory = { config, context -> createComponent(config, context, leftNavigator) }
    )

    private val rightNavigator = RightNavigator()
    private val rightNavigation = StackNavigation<FeatureConfig>()
    private val rightStack: Value<ChildStack<FeatureConfig, RenderComponent>> = childStack(
        key = "RightStack",
        source = leftNavigation,
        initialStack = { listOf(FeatureConfig.Newsfeed) },
        childFactory = { config, context -> createComponent(config, context, rightNavigator) }
    )

    private fun createComponent(featureConfig: FeatureConfig, componentContext: ComponentContext, navigator: AppNavigator): RenderComponent {
        val contextWrapper = ContextWrapperImpl(componentContext, NavigationOwnerImpl(navigator))
        return config.componentFabric.createComponent(featureConfig, contextWrapper)
    }

    @Composable
    override fun render() {
        Row (
            modifier = Modifier
                .fillMaxSize()
        ) {
            val leftChildrenStack by leftStack.subscribeAsState()
            RenderStack(leftChildrenStack, 0.3f)

            val rightChildrenStack by rightStack.subscribeAsState()
            RenderStack(rightChildrenStack, 0.7f)
        }
    }

    @Composable
    private fun RowScope.RenderStack(stack: ChildStack<FeatureConfig, RenderComponent>, weight: Float) {
        Children(
            stack = stack,
            animation = stackAnimation(slide()),
            modifier = Modifier.weight(weight)
        ) { child ->
            child.instance.render()
        }

    }

    data class LargeMainChildren(
        val leftStack: ChildStack<FeatureConfig, RenderComponent>,
        val rightStack: ChildStack<FeatureConfig, RenderComponent>
    )

    private inner class LeftNavigator : AppNavigator {
        override fun onNavigate(featureConfig: FeatureConfig) {
            leftNavigation.push(featureConfig)
        }

        override fun onButtonBack() {
            leftNavigation.pop()
        }
    }

    private inner class RightNavigator : AppNavigator {
        override fun onNavigate(featureConfig: FeatureConfig) {
            rightNavigation.push(featureConfig)
        }

        override fun onButtonBack() {
            rightNavigation.pop()
        }
    }
}