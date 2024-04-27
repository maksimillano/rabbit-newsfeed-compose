package com.maksimillano.api.model.attachment

import com.maksimillano.api.model.post.image.ImageSizeable

interface PhotoAttachment : Attachment {
    val id: Long
    val ownerId: Long
    val date: Long
    val text: String
    val thumbnail: ImageSizeable
}