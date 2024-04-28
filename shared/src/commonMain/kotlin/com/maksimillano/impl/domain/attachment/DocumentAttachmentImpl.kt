package com.maksimillano.impl.domain.attachment

import com.maksimillano.api.domain.model.attachment.DocumentAttachment

data class DocumentAttachmentImpl(
    override val id: Long,
    override val ownerId: Long,
    override val date: Long,
    override val title: String,
    override val size: Long,
    override val extension: String,
    override val url: String,
    override val docType: DocumentAttachment.DocType
) : DocumentAttachment {
}