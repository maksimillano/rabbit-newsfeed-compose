package com.maksimillano.api.model.newsfeed.markdown.entity

interface InlineCodeEntity : MarkdownEntity {
    val offsets: IntRange
}