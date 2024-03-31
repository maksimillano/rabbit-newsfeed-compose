package com.maksimillano.data.model.attachment

import com.maksimillano.api.model.attachment.PhotoAttachment

data class PhotoAttachmentImpl(
    override val id: Long,
    override val ownerId: Long,
    override val date: Long,
    override val text: String,
    override val thumbnails: List<PhotoAttachment.PhotoThumbnail>
) : PhotoAttachment {
}