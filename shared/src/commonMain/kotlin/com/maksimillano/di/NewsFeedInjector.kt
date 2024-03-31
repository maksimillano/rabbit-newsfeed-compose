package com.maksimillano.di

import com.maksimillano.api.proxy.newsfeed.NewsFeedLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object NewsFeedInjector : KoinComponent {
    fun newsfeedLoader(): NewsFeedLoader = get()
}