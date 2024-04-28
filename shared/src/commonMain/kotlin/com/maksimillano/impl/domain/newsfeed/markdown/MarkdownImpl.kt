package com.maksimillano.impl.domain.newsfeed.markdown

import com.maksimillano.api.domain.model.post.markdown.Markdown
import com.maksimillano.api.domain.model.post.markdown.entity.MarkdownEntity

data class MarkdownImpl(
    override val entities: List<MarkdownEntity>,
    override val version: Int
) : Markdown