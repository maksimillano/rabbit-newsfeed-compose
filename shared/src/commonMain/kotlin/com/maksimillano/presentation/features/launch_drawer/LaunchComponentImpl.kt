package com.maksimillano.presentation.features.launch_drawer

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.ContextWrapper
import com.maksimillano.presentation.features.navigation.NavigationComponentImpl
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponentImpl

class LaunchComponentImpl(contextWrapper: ContextWrapper) : BaseComponent(contextWrapper), LaunchComponent {
    override val navigationComponent = NavigationComponentImpl(contextWrapper.childWrapper("Navigation"))
    override val newsFeedComponent = NewsFeedComponentImpl(contextWrapper.childWrapper("NewsFeed"))

    @Composable
    override fun render() = LaunchScreen(this)
}