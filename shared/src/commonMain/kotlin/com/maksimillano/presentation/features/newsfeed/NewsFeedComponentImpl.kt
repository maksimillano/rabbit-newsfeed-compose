package com.maksimillano.presentation.features.newsfeed

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.base.ContextWrapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class NewsFeedComponentImpl(contextWrapper: ContextWrapper) : NewsFeedComponent(contextWrapper), KoinComponent {
    override val viewModel: NewsFeedViewModel = instanceKeeper.getOrCreate { get() }
    override fun createRouter() = NewsFeedRouter(navigator)

    @Composable
    override fun render() = NewsFeedScreen(this)
}