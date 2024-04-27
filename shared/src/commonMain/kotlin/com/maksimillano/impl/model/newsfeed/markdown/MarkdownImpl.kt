package com.maksimillano.impl.model.newsfeed.markdown

import com.maksimillano.api.model.post.markdown.Markdown
import com.maksimillano.api.model.post.markdown.entity.MarkdownEntity

data class MarkdownImpl(
    override val entities: List<MarkdownEntity>,
    override val version: Int
) : Markdown