package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.domain.loader.LoadMode
import com.maksimillano.api.domain.loader.Loader
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.post.PostsLoaderData
import kotlinx.coroutines.launch

class NewsFeedViewModelImpl(
    private val dependencies: NewsFeedDependencies
) : NewsFeedViewModel(NewsFeedState()) {

    init {
        observeNewsfeed()
        dependencies.newsfeedLoader.loadMore(LoadMode.Latest)
    }

    private fun observeNewsfeed() {
        launch {
            dependencies.newsfeedLoader.state
                .collect {
                    handleLoaderState(it)
                }
        }
    }

    override fun onNewEvent(event: NewsFeedMviEvent) {
        when (event) {
            is NewsFeedMviEvent.LoadLatest -> dependencies.newsfeedLoader.loadMore(LoadMode.Latest)
            is NewsFeedMviEvent.LoadBefore -> dependencies.newsfeedLoader.loadMore(LoadMode.Before(CHUNK_SIZE))
            is NewsFeedMviEvent.LoadSince -> dependencies.newsfeedLoader.loadMore(LoadMode.Since(CHUNK_SIZE))
            is NewsFeedMviEvent.SetReaction -> dependencies.postInteractor.setReaction(event.post, event.channel, event.reaction)
            is NewsFeedMviEvent.UnsetReaction -> dependencies.postInteractor.unsetReaction(event.post, event.channel)
            is NewsFeedMviEvent.SaveToFavorites -> dependencies.postInteractor.savePost(event.post, event.channel)
        }
    }

    private fun handleLoaderState(loaderState: Loader.State<PostsLoaderData>) {
        withState { state ->
            state.copy(
                feedItemEntries = dependencies.displayEntryFactory.create(loaderState.data.postHistory)
            )
        }
    }

    private companion object {
        const val CHUNK_SIZE = 25
    }
}