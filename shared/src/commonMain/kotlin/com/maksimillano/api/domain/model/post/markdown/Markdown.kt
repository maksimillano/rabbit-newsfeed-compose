package com.maksimillano.api.domain.model.post.markdown

import com.maksimillano.api.domain.model.post.markdown.entity.MarkdownEntity

interface Markdown {
    val entities: List<MarkdownEntity>
    val version: Int
}
