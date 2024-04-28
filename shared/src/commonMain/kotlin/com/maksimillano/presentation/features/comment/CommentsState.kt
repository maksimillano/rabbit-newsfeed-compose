package com.maksimillano.presentation.features.comment

import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.base.mvi.MviState

data class CommentsState(
    val post: Post? = null,
) : MviState