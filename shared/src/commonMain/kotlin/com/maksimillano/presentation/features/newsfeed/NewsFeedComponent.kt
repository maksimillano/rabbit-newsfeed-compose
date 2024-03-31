package com.maksimillano.presentation.features.newsfeed

import kotlinx.coroutines.flow.StateFlow

interface NewsFeedComponent {
    val viewModel: NewsFeedViewModel
    val state: StateFlow<NewsFeedState>
}