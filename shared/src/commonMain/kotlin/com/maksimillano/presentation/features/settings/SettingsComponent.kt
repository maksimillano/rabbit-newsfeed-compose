package com.maksimillano.presentation.features.settings

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class SettingsComponent(contextWrapper: ContextWrapper) : BaseMviComponent<SettingsState, SettingsMviEvent, SettingsNavEvent, SettingsViewAction>(contextWrapper)