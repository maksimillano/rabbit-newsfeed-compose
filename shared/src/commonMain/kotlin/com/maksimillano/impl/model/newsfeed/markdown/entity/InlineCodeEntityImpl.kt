package com.maksimillano.impl.model.newsfeed.markdown.entity

import com.maksimillano.api.model.post.markdown.entity.InlineCodeEntity

data class InlineCodeEntityImpl(
    override val offsets: IntRange
) : InlineCodeEntity {
}