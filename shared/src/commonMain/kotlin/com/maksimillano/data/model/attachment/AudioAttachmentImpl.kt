package com.maksimillano.data.model.attachment

import com.maksimillano.api.model.attachment.AudioAttachment

data class AudioAttachmentImpl(
    override val id: Int,
    override val ownerId: Int,
    override val date: Long,
    override val title: String,
    override val artist: String,
    override val duration: Long,
    override val url: String
) : AudioAttachment {
}