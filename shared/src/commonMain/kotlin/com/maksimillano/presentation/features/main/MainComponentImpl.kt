package com.maksimillano.presentation.features.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.maksimillano.presentation.base.ViewEntry
import com.maksimillano.presentation.features.comment.CommentComponentImpl
import com.maksimillano.presentation.features.comment.CommentScreen
import com.maksimillano.presentation.features.launch.LaunchComponentImpl
import com.maksimillano.presentation.features.launch.LaunchScreen

class MainComponentImpl(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainAppEntry>()
    override val state: Value<ChildStack<MainAppEntry, ViewEntry>> = childStack(
        source = navigation,
        initialStack = { listOf(MainAppEntry.Launch) },
        childFactory = ::createComponent
    )

    private fun createComponent(entry: MainAppEntry, componentContext: ComponentContext): ViewEntry {
        return when (entry) {
            is MainAppEntry.Launch -> ViewEntry {
                LaunchScreen(
                    LaunchComponentImpl(componentContext, this)
                )
            }
            is MainAppEntry.Comment -> ViewEntry {
                CommentScreen(
                    CommentComponentImpl(componentContext, this)
                )
            }
        }
    }

    override fun openComments() {
        navigation.push(MainAppEntry.Comment)
    }
}