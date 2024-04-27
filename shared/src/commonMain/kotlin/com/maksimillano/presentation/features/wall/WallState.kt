package com.maksimillano.presentation.features.wall

import com.maksimillano.presentation.base.mvi.MviState
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

data class WallState(
    val avatar: String = "",
    val title: String = "",
    val subtitle: String = "",
    val items: List<FeedDisplayEntry> = emptyList()
) : MviState