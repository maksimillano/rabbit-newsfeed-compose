package com.maksimillano.presentation.features.launch_drawer

import com.maksimillano.presentation.features.navigation.NavigationComponent
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponent

interface LaunchComponent {
    val navigationComponent: NavigationComponent
    val newsFeedComponent: NewsFeedComponent
}