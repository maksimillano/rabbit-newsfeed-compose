package com.maksimillano.presentation.features.newsfeed

interface NewsFeedMviEvent {
    data object LoadLatest : NewsFeedMviEvent
    data class LoadSince(val weight: Long) : NewsFeedMviEvent
    data class LoadBefore(val weight: Long) : NewsFeedMviEvent
}