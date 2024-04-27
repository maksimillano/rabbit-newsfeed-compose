package com.maksimillano.presentation.features.favorites

import com.maksimillano.presentation.base.mvi.MviState
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

data class FavoritesState(
    val entries: List<FeedDisplayEntry> = emptyList()
) : MviState
