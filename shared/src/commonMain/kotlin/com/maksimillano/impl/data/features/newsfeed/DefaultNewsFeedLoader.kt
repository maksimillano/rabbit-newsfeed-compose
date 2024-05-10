package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.loader.LoadMode
import com.maksimillano.api.domain.loader.Loader
import com.maksimillano.api.domain.features.post.PostsLoader
import com.maksimillano.api.domain.features.post.PostsLoaderData
import com.maksimillano.impl.domain.newsfeed.post.PostHistoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultNewsFeedLoader : PostsLoader {
    private val workScope = CoroutineScope(Dispatchers.Default)

    private val _data: MutableStateFlow<Loader.State<PostsLoaderData>> = MutableStateFlow(
        Loader.State(PostsLoaderDataImpl(PostHistoryImpl()), true)
    )
    override val state: StateFlow<Loader.State<PostsLoaderData>>
        get() = _data

    override fun loadMore(loadMode: LoadMode) {
        workScope.launch {
//            delay(2000)
            val history = MockFeedGenerator.generate()
            _data.tryEmit(Loader.State(PostsLoaderDataImpl(history), false))
        }
    }

    override fun destroy() {
        workScope.cancel()
    }

    override fun reset() {
    }

    override fun resetAndLoad() {
    }
}