package com.maksimillano.impl.domain.attachment

import com.maksimillano.api.domain.model.attachment.VideoAttachment
import com.maksimillano.api.domain.model.post.image.ImageSizeable

data class VideoAttachmentImpl(
    override val id: Int,
    override val ownerId: Int,
    override val date: Long,
    override val title: String,
    override val description: String,
    override val duration: Int,
    override val videoType: VideoAttachment.VideoType,
    override val image: ImageSizeable,
    override val views: Int,
    override val player: String,
    override val width: Int,
    override val height: Int
) : VideoAttachment