package com.maksimillano.presentation.features.launch

import com.maksimillano.presentation.features.newsfeed.NewsFeedComponent

interface LaunchComponent {
    val viewModel: LaunchViewModel
    val newsFeedComponent: NewsFeedComponent
    fun updateTheme()
    fun logout()
}