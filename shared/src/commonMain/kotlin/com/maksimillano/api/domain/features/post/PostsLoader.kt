package com.maksimillano.api.domain.features.post

import com.maksimillano.api.domain.loader.Loader
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.api.domain.model.post.PostHistory

interface PostsLoader : Loader<PostsLoaderData> {
    fun onRefresh(refreshHistory: PostHistory)
    fun onUpdate(posts: List<Post>)
}