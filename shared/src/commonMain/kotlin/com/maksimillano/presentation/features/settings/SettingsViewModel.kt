package com.maksimillano.presentation.features.settings

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class SettingsViewModel(initState: SettingsState) : BaseMviViewModel<SettingsState, SettingsMviEvent, SettingsNavEvent, SettingsViewAction>(initState)