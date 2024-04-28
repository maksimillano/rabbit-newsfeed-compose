package com.maksimillano.api.domain.model.attachment

interface DocumentAttachment : Attachment {
    val id: Long
    val ownerId: Long
    val date: Long
    val title: String
    val size: Long
    val extension: String
    val url: String
    val docType: DocType

    enum class DocType {
        TXT,
        ARCHIVES,
        GIF,
        IMAGE,
        AUDIO,
        VIDEO,
        EBOOK,
        UNKNOWN
    }
}