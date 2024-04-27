package com.maksimillano.presentation.features.favorites

import com.maksimillano.presentation.base.mvi.MviEvent

sealed interface FavoritesMviEvent : MviEvent {
    data class OpenPost(val postId: Long, val ownerId: Long)
}