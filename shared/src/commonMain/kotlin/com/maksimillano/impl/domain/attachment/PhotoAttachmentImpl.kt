package com.maksimillano.impl.domain.attachment

import com.maksimillano.api.domain.model.attachment.PhotoAttachment
import com.maksimillano.api.domain.model.post.image.ImageSizeable

data class PhotoAttachmentImpl(
    override val id: Long,
    override val ownerId: Long,
    override val date: Long,
    override val text: String,
    override val thumbnail: ImageSizeable
) : PhotoAttachment