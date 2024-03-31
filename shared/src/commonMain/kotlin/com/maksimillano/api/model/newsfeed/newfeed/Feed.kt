package com.maksimillano.api.model.newsfeed.newfeed

sealed interface Feed {
    val id: String
    val sourceId: Long
    val date: Long
    val weight: Long
}