package com.maksimillano.api.model.post.newfeed

sealed interface Feed {
    val id: String
    val sourceId: Long
    val date: Long
}