package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory
import kotlinx.coroutines.delay

class NewsFeedDependenciesImpl(
    feedDisplayEntryFactory: NewsFeedDisplayEntryFactory
) : NewsFeedDependencies {
    override val displayEntryFactory = feedDisplayEntryFactory
    override val newsfeedLoader = DefaultNewsFeedLoader()
    override val pageItemsCount: Int = 25

    override val savePostInteractor = SavePostInteractorImpl()
    override val setReactionInteractor = SetReactionInteractorImpl()
    override val unsetReactionInteractor = UnsetReactionInteractorImpl()
    override val updatePostsInteractor = UpdatePostsInteractorImpl()
    override val refreshPostsInteractor = RefreshPostsInteractorImpl(newsfeedLoader)

    class SavePostInteractorImpl : NewsFeedDependencies.SavePostInteractor {
        override suspend fun execute(postId: Long, channelId: Long) {
        }
    }
    class SetReactionInteractorImpl : NewsFeedDependencies.SetReactionInteractor {
        override suspend fun execute(postId: Long, channelId: Long, reaction: Int) {
        }
    }
    class UnsetReactionInteractorImpl : NewsFeedDependencies.UnsetReactionInteractor {
        override suspend fun execute(postId: Long, channelId: Long) {
        }
    }
    class UpdatePostsInteractorImpl : NewsFeedDependencies.UpdatePostsInteractor {
        override suspend fun execute(postsIds: List<Long>) {
        }
    }
    class RefreshPostsInteractorImpl(private val newsfeedLoader: DefaultNewsFeedLoader) : NewsFeedDependencies.RefreshPostsInteractor {
        override suspend fun execute() {
            val freshPage = 1
            newsfeedLoader.onRefresh(MockFeedGenerator.generate(freshPage))
        }
    }
}