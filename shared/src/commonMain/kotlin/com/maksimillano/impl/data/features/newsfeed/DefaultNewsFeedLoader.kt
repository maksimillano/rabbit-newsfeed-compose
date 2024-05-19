package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.post.PostsLoader
import com.maksimillano.api.domain.features.post.PostsLoaderData
import com.maksimillano.api.domain.loader.LoadMode
import com.maksimillano.api.domain.loader.Loader
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.impl.domain.newsfeed.post.PostHistoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultNewsFeedLoader : PostsLoader {
    private val workScope = CoroutineScope(Dispatchers.Default)

    private val _data: MutableStateFlow<Loader.State<PostsLoaderData>> = MutableStateFlow(
        Loader.State(PostsLoaderDataImpl(PostHistoryImpl()), true)
    )

    override fun onRefresh(refreshHistory: PostHistory) {
        workScope.launch {
            mutex.withLock {
                delay(SIMULATE_LOAD_DELAY)

                val postHistory = _data.value.data.postHistory
                val newHistory = PostHistoryImpl(
                    refreshHistory.feeds + postHistory.feeds,
                    (refreshHistory.users + postHistory.users).distinct(),
                    (refreshHistory.channels + postHistory.channels).distinct(),
                )
                _data.emit(
                    Loader.State(PostsLoaderDataImpl(newHistory), false)
                )
            }
        }
    }

    override fun onUpdate(posts: List<Post>) {
    }

    override val state: StateFlow<Loader.State<PostsLoaderData>>
        get() = _data
    private val mutex = Mutex()
    private var page = 0

    override fun loadMore(loadMode: LoadMode) {
        workScope.launch {
            if (mutex.isLocked) return@launch
            mutex.withLock {
                delay(SIMULATE_LOAD_DELAY)
                val history = MockFeedGenerator.generate(page++)
                val postHistory = _data.value.data.postHistory
                val newHistory = PostHistoryImpl(
                    postHistory.feeds + history.feeds,
                    (postHistory.users + history.users).distinct(),
                    (postHistory.channels + history.channels).distinct(),
                )
                _data.emit(
                    Loader.State(PostsLoaderDataImpl(newHistory), false)
                )
            }
        }
    }

    override fun destroy() {
        workScope.cancel()
    }

    override fun reset() {
    }

    override fun resetAndLoad() {
    }

    private companion object {
        const val SIMULATE_LOAD_DELAY = 2000L
    }
}