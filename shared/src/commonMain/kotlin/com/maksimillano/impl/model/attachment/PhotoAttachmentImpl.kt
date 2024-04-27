package com.maksimillano.impl.model.attachment

import com.maksimillano.api.model.attachment.PhotoAttachment
import com.maksimillano.api.model.post.image.ImageSizeable

data class PhotoAttachmentImpl(
    override val id: Long,
    override val ownerId: Long,
    override val date: Long,
    override val text: String,
    override val thumbnail: ImageSizeable
) : PhotoAttachment