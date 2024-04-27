package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.data.loader.Direction
import com.maksimillano.api.data.posts.PostsLoader
import com.maksimillano.api.data.posts.PostsLoaderData
import com.maksimillano.api.data.newsfeed.NewsFeedDependencies
import kotlinx.coroutines.launch

class NewsFeedViewModelImpl(
    private val postsLoader: PostsLoader,
    private val newsFeedDependencies: NewsFeedDependencies,
    private val feedDisplayEntryFactory: FeedDisplayEntryFactory
) : NewsFeedViewModel(NewsFeedState()) {

    init {
        observeNewsfeed()
        postsLoader.loadMore(Direction.LATEST)
    }

    private fun observeNewsfeed() {
        launch {
            postsLoader.data
                .collect {
                    handleLoaderState(it)
                }
        }
    }

    override fun onNewEvent(event: NewsFeedMviEvent) {
        when (event) {
            is NewsFeedMviEvent.LoadLatest -> postsLoader.loadMore(Direction.LATEST)
            is NewsFeedMviEvent.LoadBefore -> postsLoader.loadMore(Direction.BEFORE)
            is NewsFeedMviEvent.LoadSince -> postsLoader.loadMore(Direction.SINCE)
            is NewsFeedMviEvent.SetReaction -> newsFeedDependencies.postInteractor.setReaction(event.post, event.channel, event.reaction)
            is NewsFeedMviEvent.UnsetReaction -> newsFeedDependencies.postInteractor.unsetReaction(event.post, event.channel)
            is NewsFeedMviEvent.SaveToFavorites -> newsFeedDependencies.postInteractor.savePost(event.post, event.channel)
        }
    }

    private fun handleLoaderState(loaderData: PostsLoaderData) {
        withState {  state ->
            state.copy(
                feedItemEntries = feedDisplayEntryFactory.create(loaderData.postHistory)
            )
        }
    }
}