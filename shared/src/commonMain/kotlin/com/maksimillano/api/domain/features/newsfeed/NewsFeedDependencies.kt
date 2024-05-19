package com.maksimillano.api.domain.features.newsfeed

import com.maksimillano.api.domain.features.post.PostsLoader

interface NewsFeedDependencies {
    val displayEntryFactory: NewsFeedDisplayEntryFactory
    val newsfeedLoader: PostsLoader
    val pageItemsCount: Int

    val savePostInteractor: SavePostInteractor
    val setReactionInteractor: SetReactionInteractor
    val unsetReactionInteractor: UnsetReactionInteractor
    val updatePostsInteractor: UpdatePostsInteractor
    val refreshPostsInteractor: RefreshPostsInteractor

    interface SavePostInteractor {
        suspend fun execute(postId: Long, channelId: Long)
    }
    interface SetReactionInteractor {
        suspend fun execute(postId: Long, channelId: Long, reaction: Int)
    }
    interface UnsetReactionInteractor {
        suspend fun execute(postId: Long, channelId: Long)
    }
    interface UpdatePostsInteractor {
        suspend fun execute(postsIds: List<Long>)
    }
    interface RefreshPostsInteractor {
        suspend fun execute()
    }
}