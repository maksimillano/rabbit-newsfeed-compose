package com.maksimillano.api.model.post.markdown

import com.maksimillano.api.model.post.markdown.entity.MarkdownEntity

interface Markdown {
    val entities: List<MarkdownEntity>
    val version: Int
}
