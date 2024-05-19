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
        dependencies.newsfeedLoader.loadMore(LoadMode.Before(dependencies.pageItemsCount))
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
            is NewsFeedMviEvent.SetReaction -> launch { dependencies.setReactionInteractor.execute(event.post, event.channel, event.reaction) }
            is NewsFeedMviEvent.UnsetReaction -> launch { dependencies.unsetReactionInteractor.execute(event.post, event.channel) }
            is NewsFeedMviEvent.SaveToFavorites -> launch { dependencies.savePostInteractor.execute(event.post, event.channel) }
            is NewsFeedMviEvent.RefreshPage -> {
                withState { state ->
                    state.copy(refreshStatus = RefreshStatus.Show)
                }
                launch { dependencies.refreshPostsInteractor.execute() }
            }
            is NewsFeedMviEvent.UpdatePosts -> {
                // dependencies.updatePostsInteractor.execute() todo: access history and pass history by range
            }
        }
    }

    private fun handleLoaderState(loaderState: Loader.State<PostsLoaderData>) {
        withState { state ->
            state.copy(
                feedItemEntries = dependencies.displayEntryFactory.create(loaderState.data.postHistory),
                refreshStatus = RefreshStatus.Missing
            )
        }
    }

    private companion object {
        const val CHUNK_SIZE = 25
    }
}