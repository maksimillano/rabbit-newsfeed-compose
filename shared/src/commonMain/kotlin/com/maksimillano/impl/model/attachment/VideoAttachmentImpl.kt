package com.maksimillano.impl.model.attachment

import com.maksimillano.api.model.attachment.VideoAttachment
import com.maksimillano.api.model.post.image.ImageSizeable

data class VideoAttachmentImpl(
    override val id: Int,
    override val ownerId: Int,
    override val date: Long,
    override val title: String,
    override val description: String,
    override val duration: Int,
    override val type: VideoAttachment.Type,
    override val image: ImageSizeable,
    override val views: Int,
    override val player: String,
    override val width: Int,
    override val height: Int
) : VideoAttachment