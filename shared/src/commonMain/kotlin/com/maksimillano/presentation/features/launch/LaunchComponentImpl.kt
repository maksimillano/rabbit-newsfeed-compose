package com.maksimillano.presentation.features.launch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponentImpl
import com.maksimillano.presentation.features.main.MainComponent

class LaunchComponentImpl(
    componentContext: ComponentContext,
    mainComponent: MainComponent
): LaunchComponent, ComponentContext by componentContext {

    override val newsFeedComponent = NewsFeedComponentImpl(
        componentContext,
        mainComponent
    )
    override val viewModel = instanceKeeper.getOrCreate { LaunchViewModel() }

    sealed class Page {
        data object Feed : Page()
        data object Popular : Page()
        data object News : Page()
        data object Clips : Page()
    }

    /**
     * TODO
     * Также возможность кастом папки со своими листами подписок (Есть поддержка вк апи)
     * При выходе мы запоминаем последнию папку и открываем ИЛИ/И возможность переноса папок
     */
    private val pages = listOf(
        Page.Feed, Page.Popular, Page.News, Page.Clips
    )

    override fun updateTheme() {
        viewModel.updateTheme()
    }

    override fun logout() {
        viewModel.logout()
    }
}