package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface ChannelsMviEvent : MviEvent {
    data class OpenChannel(val channelId: Long) : ChannelsMviEvent
    data class DeleteChannel(val channelId: Long) : ChannelsMviEvent
    data class PinChannel(val channelId: Long) : ChannelsMviEvent
    data class SubscribeChannel(val channelId: Long) : ChannelsMviEvent
    data object CreateChannel : ChannelsMviEvent

    data class LoadSince(val since: Long) : ChannelsMviEvent
    data class LoadBefore(val before: Long) : ChannelsMviEvent
}