package com.maksimillano.presentation.features.comment

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class CommentsViewModel(initState: CommentsState) : BaseMviViewModel<CommentsState, CommentsMviEvent, CommentsNavEvent, CommentsViewAction>(initState)