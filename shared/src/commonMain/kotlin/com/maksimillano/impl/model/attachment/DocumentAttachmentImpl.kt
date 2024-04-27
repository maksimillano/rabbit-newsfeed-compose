package com.maksimillano.impl.model.attachment

import com.maksimillano.api.model.attachment.DocumentAttachment

data class DocumentAttachmentImpl(
    override val id: Long,
    override val ownerId: Long,
    override val date: Long,
    override val title: String,
    override val size: Long,
    override val extension: String,
    override val url: String,
    override val type: DocumentAttachment.Type,
    override val previewInfo: DocumentAttachment.PreviewInfo?
) : DocumentAttachment {
}