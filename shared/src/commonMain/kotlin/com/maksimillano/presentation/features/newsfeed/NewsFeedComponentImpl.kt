package com.maksimillano.presentation.features.newsfeed

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import com.maksimillano.presentation.features.main.MainComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class NewsFeedComponentImpl(
    componentContext: ComponentContext,
    private val mainComponent: MainComponent
) : NewsFeedComponent, KoinComponent, ComponentContext by componentContext {
    override val viewModel: NewsFeedViewModel = instanceKeeper.getOrCreate { get() }
    override val state: StateFlow<NewsFeedState> = viewModel.state
}