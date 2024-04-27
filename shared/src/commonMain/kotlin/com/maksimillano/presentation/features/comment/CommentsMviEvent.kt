package com.maksimillano.presentation.features.comment

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface CommentsMviEvent : MviEvent {
    data class SetReaction(val reactionId: Int) : CommentsMviEvent
    data class UnsetReaction(val reactionId: Int) : CommentsMviEvent
    data class SetCommentReaction(val commentId: Long, val reactionId: Int) : CommentsMviEvent
    data class UnsetCommentReaction(val commentId: Long, val reactionId: Int) : CommentsMviEvent

    data class LoadSince(val sinceComment: Long, val threadComment: Long? = null) : CommentsMviEvent
    data class LoadBefore(val beforeComment: Long, val threadComment: Long? = null) : CommentsMviEvent

    data class Delete(val comment: Long) : CommentsMviEvent
    data class Edit(val comment: Long) : CommentsMviEvent
    data class Copy(val comment: Long) : CommentsMviEvent
    data class Share(val comment: Long) : CommentsMviEvent
}