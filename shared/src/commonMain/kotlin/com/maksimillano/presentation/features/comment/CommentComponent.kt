package com.maksimillano.presentation.features.comment

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class CommentComponent(contextWrapper: ContextWrapper) : BaseMviComponent<CommentsState, CommentsMviEvent, CommentsNavEvent, CommentsViewAction>(contextWrapper)