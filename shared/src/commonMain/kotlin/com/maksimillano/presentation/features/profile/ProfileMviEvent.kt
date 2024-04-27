package com.maksimillano.presentation.features.profile

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface ProfileMviEvent : MviEvent {
    data object AvatarOpen : ProfileMviEvent
}