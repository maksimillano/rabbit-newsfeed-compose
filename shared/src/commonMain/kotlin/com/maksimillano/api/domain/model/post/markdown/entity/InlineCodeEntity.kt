package com.maksimillano.api.domain.model.post.markdown.entity

interface InlineCodeEntity : MarkdownEntity {
    val offsets: IntRange
}