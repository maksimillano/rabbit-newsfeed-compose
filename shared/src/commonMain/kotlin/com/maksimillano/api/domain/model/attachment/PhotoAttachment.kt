package com.maksimillano.api.domain.model.attachment

import com.maksimillano.api.domain.model.post.image.ImageSizeable

interface PhotoAttachment : Attachment {
    val id: Long
    val ownerId: Long
    val date: Long
    val text: String
    val thumbnail: ImageSizeable
}