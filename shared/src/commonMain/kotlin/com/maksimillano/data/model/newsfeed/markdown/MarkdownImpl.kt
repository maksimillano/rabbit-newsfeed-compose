package com.maksimillano.data.model.newsfeed.markdown

import com.maksimillano.api.model.newsfeed.markdown.Markdown
import com.maksimillano.api.model.newsfeed.markdown.entity.MarkdownEntity

data class MarkdownImpl(
    override val entities: List<MarkdownEntity>,
    override val version: Int
) : Markdown