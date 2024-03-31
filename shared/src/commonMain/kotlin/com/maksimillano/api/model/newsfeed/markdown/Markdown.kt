package com.maksimillano.api.model.newsfeed.markdown

import com.maksimillano.api.model.newsfeed.markdown.entity.MarkdownEntity

interface Markdown {
    val entities: List<MarkdownEntity>
    val version: Int
}
