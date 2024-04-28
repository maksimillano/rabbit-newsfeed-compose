package com.maksimillano.api.domain.model.post

sealed interface Postable {
    val id: String
    val sourceId: Long
    val date: Long
}