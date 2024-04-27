package com.maksimillano.api.model.post.markdown.entity

interface InlineCodeEntity : MarkdownEntity {
    val offsets: IntRange
}