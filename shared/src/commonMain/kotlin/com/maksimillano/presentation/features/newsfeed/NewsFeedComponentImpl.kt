package com.maksimillano.presentation.features.newsfeed

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.base.ContextWrapper
import com.maksimillano.util.localPostTextStyleProvider
import com.maksimillano.util.localPostWidthProvider
import com.maksimillano.util.localTextMeasurerProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class NewsFeedComponentImpl(contextWrapper: ContextWrapper) : NewsFeedComponent(contextWrapper), KoinComponent {
    override val viewModel: NewsFeedViewModel = instanceKeeper.getOrCreate {
        get(parameters = { parametersOf(localPostWidthProvider, localTextMeasurerProvider, localPostTextStyleProvider) })
    }
    override fun createRouter() = NewsFeedRouter(navigator)

    @Composable
    override fun render() = NewsFeedScreen(this)
}