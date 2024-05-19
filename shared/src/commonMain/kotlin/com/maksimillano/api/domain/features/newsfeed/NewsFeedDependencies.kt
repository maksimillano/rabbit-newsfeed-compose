package com.maksimillano.api.domain.features.newsfeed

import com.maksimillano.api.domain.features.post.PostsLoaderData
import com.maksimillano.api.domain.loader.Loader

interface NewsFeedDependencies {
    val displayEntryFactory: NewsFeedDisplayEntryFactory
    val newsfeedLoader: Loader<PostsLoaderData>
    val pageItemsCount: Int

    val savePostInteractor: SavePostInteractor
    val setReactionInteractor: SetReactionInteractor
    val unsetReactionInteractor: UnsetReactionInteractor
    val updatePostsInteractor: UpdatePostsInteractor
    val refreshPostsInteractor: RefreshPostsInteractor

    interface SavePostInteractor {
        fun execute(postId: Long, channelId: Long)
    }
    interface SetReactionInteractor {
        fun execute(postId: Long, channelId: Long, reaction: Int)
    }
    interface UnsetReactionInteractor {
        fun execute(postId: Long, channelId: Long)
    }
    interface UpdatePostsInteractor {
        fun execute(postsIds: List<Long>)
    }
    interface RefreshPostsInteractor {
        fun execute()
    }
}