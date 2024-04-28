package com.maksimillano.api.domain.features.post

import com.maksimillano.api.domain.loader.LoaderData
import com.maksimillano.api.domain.model.post.PostHistory

interface PostsLoaderData : LoaderData {
    val postHistory: PostHistory
}