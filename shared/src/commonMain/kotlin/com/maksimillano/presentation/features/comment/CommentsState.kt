package com.maksimillano.presentation.features.comment

import com.maksimillano.api.model.post.newfeed.PostFeed
import com.maksimillano.presentation.base.mvi.MviState

data class CommentsState(
    val post: PostFeed? = null,
) : MviState