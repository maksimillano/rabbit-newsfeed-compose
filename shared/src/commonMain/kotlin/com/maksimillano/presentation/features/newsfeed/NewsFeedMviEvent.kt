package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.MviEvent

interface NewsFeedMviEvent : MviEvent {
    data object LoadLatest : NewsFeedMviEvent
    data object LoadSince : NewsFeedMviEvent
    data object LoadBefore : NewsFeedMviEvent

    data class Share(val peer: Long, val post: Long) : NewsFeedMviEvent
    data class SaveToFavorites(val channel: Long, val post: Long) : NewsFeedMviEvent
    data class SetReaction(val channel: Long, val post: Long, val reaction: Int) : NewsFeedMviEvent
    data class UnsetReaction(val channel: Long, val post: Long, val reaction: Int) : NewsFeedMviEvent

    data object RefreshPage : NewsFeedMviEvent
    data class UpdatePosts(val visiblePostIndexes: IntRange) : NewsFeedMviEvent
}