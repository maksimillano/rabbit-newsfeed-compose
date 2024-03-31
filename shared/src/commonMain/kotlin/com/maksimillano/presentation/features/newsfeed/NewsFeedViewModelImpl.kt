package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.preference.UserPreferences
import com.maksimillano.api.proxy.newsfeed.NewsFeedLoader
import com.maksimillano.api.proxy.newsfeed.NewsFeedLoaderEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsFeedViewModelImpl(
    private val newsFeedLoader: NewsFeedLoader,
    private val userPreferences: UserPreferences
) : NewsFeedViewModel {
    private val stateProducer: MutableStateFlow<NewsFeedPartialState> = MutableStateFlow(
        NewsFeedMutations.empty()
    )

    override val state: StateFlow<NewsFeedState> = stateProducer
        .scan(NewsFeedState()) { accumulator, mutation -> mutation(accumulator) }
        .distinctUntilChanged()
        .onStart { observeLoaders() }
        .stateIn(this, SharingStarted.Eagerly, NewsFeedState())

    override fun postEvent(event: NewsFeedMviEvent) {
        when (event) {
            is NewsFeedMviEvent.LoadLatest -> loadLatest()
            is NewsFeedMviEvent.LoadBefore -> loadBefore(event.weight)
            is NewsFeedMviEvent.LoadSince -> loadSince(event.weight)
        }
    }

    private fun loadLatest() {
        if (!newsFeedLoader.isLoading) {
            launch {
                newsFeedLoader.loadLatest()
            }
        }
    }

    private fun loadSince(weight: Long) {
        if (!newsFeedLoader.isLoading && newsFeedLoader.hasHistoryAfter) {
            launch {
                newsFeedLoader.loadSince(weight)
            }
        }
    }

    private fun loadBefore(weight: Long) {
        if (!newsFeedLoader.isLoading && newsFeedLoader.hasHistoryBefore) {
            launch {
                newsFeedLoader.loadBefore(weight)
            }
        }
    }

    private fun observeLoaders() {
        launch {
            newsFeedLoader.events
                .collect {
                    handleLoaderState(it)
                }
        }
    }

    private suspend fun handleLoaderState(loaderState: NewsFeedLoaderEvent) {
        val mutation = when (loaderState) {
            is NewsFeedLoaderEvent.FailedEvent -> {
                NewsFeedMutations.onFailure(
                    mapError(loaderState.reason)
                )
            }
            is NewsFeedLoaderEvent.FeedLoadedEvent -> {
                if (loaderState.isActual) {
                    NewsFeedMutations.onActualResult(loaderState.feedHistory)
                } else {
                    NewsFeedMutations.onCacheResult(loaderState.feedHistory)
                }
            }

            is NewsFeedLoaderEvent.HasPrefetchedEvent -> {
                TODO()
            }
        }
        mutation.post()
    }

    private fun mapError(reason: String): Error {
        // TODO check error, maybe use throwable for mapping
        return Error.Unknown
    }

    suspend fun NewsFeedPartialState.post() {
        stateProducer.emit(this)
    }
}