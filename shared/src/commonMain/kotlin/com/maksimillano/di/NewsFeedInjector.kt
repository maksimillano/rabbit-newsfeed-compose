package com.maksimillano.di

import com.maksimillano.api.domain.features.post.PostsLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object NewsFeedInjector : KoinComponent {
    fun newsfeedLoader(): PostsLoader = get()
}