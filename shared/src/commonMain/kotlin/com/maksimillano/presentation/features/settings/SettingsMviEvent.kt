package com.maksimillano.presentation.features.settings

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface SettingsMviEvent : MviEvent {
    data object EditProfile : SettingsMviEvent
    data object EditAvatar : SettingsMviEvent
    data object EditPhoneNumber : SettingsMviEvent
    data object EditAbout : SettingsMviEvent

    data object AppVersion : SettingsMviEvent
    data object PolicyPrivacy : SettingsMviEvent
}

sealed interface ClickItem : SettingsMviEvent {
    data object Style : ClickItem
    data object Notification : ClickItem
    data object Language : ClickItem
    data object AskQuestion : ClickItem
}