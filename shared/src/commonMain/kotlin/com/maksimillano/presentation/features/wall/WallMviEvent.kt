package com.maksimillano.presentation.features.wall

import com.maksimillano.presentation.base.mvi.MviEvent

interface WallMviEvent : MviEvent {
    data object OpenAvatar : WallMviEvent
    data object OpenProfile : WallMviEvent
    data class OpenComments(val postId: String) : WallMviEvent
    data object CreatePost : WallMviEvent

    data class SetReaction(val postId: Long, val reactionId: Int) : WallMviEvent
    data class UnsetReaction(val postId: Long, val reactionId: Int) : WallMviEvent

    data class CopyText(val postId: String) : WallMviEvent
    data class ForwardText(val postId: String) : WallMviEvent
    data class SharePost(val postId: String) : WallMviEvent
    data class EditPost(val postId: String) : WallMviEvent
    data class DeletePost(val postId: String) : WallMviEvent
}