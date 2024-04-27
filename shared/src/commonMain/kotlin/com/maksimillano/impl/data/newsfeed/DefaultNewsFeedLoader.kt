package com.maksimillano.impl.data.newsfeed

import com.maksimillano.api.data.loader.Direction
import com.maksimillano.api.data.posts.PostsLoader
import com.maksimillano.api.data.posts.PostsLoaderData
import com.maksimillano.impl.model.newsfeed.post.PostHistoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultNewsFeedLoader : PostsLoader {
    private val workScope = CoroutineScope(Dispatchers.Default)

    private val _data: MutableStateFlow<PostsLoaderData> = MutableStateFlow(PostsLoaderData(
        PostHistoryImpl()
    ))
    override val data: Flow<PostsLoaderData>
        get() = _data

    override fun loadMore(direction: Direction) {
        workScope.launch {
            delay(2000)
            val history = MockFeedGenerator.generate()
            _data.tryEmit(PostsLoaderData(history))
        }
    }

    override fun destroy() {
        workScope.cancel()
    }
}