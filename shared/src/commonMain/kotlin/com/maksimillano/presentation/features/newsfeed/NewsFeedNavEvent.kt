package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.MviNavigationEvent

sealed interface NewsFeedNavEvent : MviNavigationEvent {
    data class OpenChannel(val peer: Long): NewsFeedNavEvent
    data class OpenComments(val peer: Long, val post: Long): NewsFeedNavEvent
}