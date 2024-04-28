package com.maksimillano.api.domain.model.attachment

interface AudioAttachment : Attachment {
    val id: Int
    val ownerId: Int
    val date: Long
    val title: String
    val artist: String
    val duration: Long
    val url: String
}
