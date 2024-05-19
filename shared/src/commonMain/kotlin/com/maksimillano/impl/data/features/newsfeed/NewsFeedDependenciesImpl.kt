package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory

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
    override val refreshPostsInteractor = RefreshPostsInteractorImpl()

    class SavePostInteractorImpl : NewsFeedDependencies.SavePostInteractor {
        override fun execute(postId: Long, channelId: Long) {
        }
    }
    class SetReactionInteractorImpl : NewsFeedDependencies.SetReactionInteractor {
        override fun execute(postId: Long, channelId: Long, reaction: Int) {
        }
    }
    class UnsetReactionInteractorImpl : NewsFeedDependencies.UnsetReactionInteractor {
        override fun execute(postId: Long, channelId: Long) {
        }
    }
    class UpdatePostsInteractorImpl : NewsFeedDependencies.UpdatePostsInteractor {
        override fun execute(postsIds: List<Long>) {
        }
    }
    class RefreshPostsInteractorImpl : NewsFeedDependencies.RefreshPostsInteractor {
        override fun execute() {
        }
    }
}