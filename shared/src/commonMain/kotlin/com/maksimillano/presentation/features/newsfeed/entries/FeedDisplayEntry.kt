package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
sealed interface FeedDisplayEntry {
    val key: String
    @Composable
    fun onBind()
}