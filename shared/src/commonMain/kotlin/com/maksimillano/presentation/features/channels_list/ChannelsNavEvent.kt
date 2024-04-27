package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.MviNavigationEvent

sealed interface ChannelsNavEvent : MviNavigationEvent {
    data object BaseNavigationEvent : ChannelsNavEvent
}