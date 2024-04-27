package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.AppNavigator

class NewsFeedRouter(
    private val appNavigator: AppNavigator
) : MviRouter<NewsFeedNavEvent> {
    override fun onHandleNavigationEvent(event: NewsFeedNavEvent) {

    }
}