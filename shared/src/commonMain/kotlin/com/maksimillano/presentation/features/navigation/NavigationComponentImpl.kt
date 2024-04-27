package com.maksimillano.presentation.features.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper
import com.maksimillano.presentation.features.FeatureConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class NavigationComponentImpl(contextWrapper: ContextWrapper) : NavigationComponent(contextWrapper), KoinComponent {
    override val viewModel: NavigationViewModel = instanceKeeper.getOrCreate { get() }
    var closeDrawerAction: (() -> Unit)? = null

    override fun createRouter(): MviRouter<NavigationNavEvent> = MviRouter { event ->
        closeDrawerAction?.invoke()

        when (event) {
            is NavigationNavEvent.OpenSettings -> navigator.onNavigate(FeatureConfig.Settings)
        }
    }

    @Composable
    override fun render() = NavigationScreen(this)
}