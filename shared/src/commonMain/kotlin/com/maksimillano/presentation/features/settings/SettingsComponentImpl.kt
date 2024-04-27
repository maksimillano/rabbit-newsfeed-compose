package com.maksimillano.presentation.features.settings

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper

class SettingsComponentImpl(contextWrapper: ContextWrapper) : SettingsComponent(contextWrapper) {
    override val viewModel: SettingsViewModel = instanceKeeper.getOrCreate { SettingsViewModelImpl() }

    override fun createRouter(): MviRouter<SettingsNavEvent> = MviRouter {
    }

    @Composable
    override fun render() = SettingsScreen(this)
}