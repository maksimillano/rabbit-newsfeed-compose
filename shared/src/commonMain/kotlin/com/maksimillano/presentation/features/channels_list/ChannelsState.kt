package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.MviState

data class ChannelsState(
    val userChannels: List<ChannelItem> = emptyList(),
    val recommended: List<ChannelRecommendItem> = emptyList()
) : MviState {
    data class ChannelItem(
        val avatar: String,
        val title: String,
        val unread: Int,
        val lastMessage: String,
        val isPinned: Boolean
    )

    data class ChannelRecommendItem(
        val avatar: String,
        val title: String
    )
}