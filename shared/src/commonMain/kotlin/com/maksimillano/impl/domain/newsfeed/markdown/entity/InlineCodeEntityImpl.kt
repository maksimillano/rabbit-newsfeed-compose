package com.maksimillano.impl.domain.newsfeed.markdown.entity

import com.maksimillano.api.domain.model.post.markdown.entity.InlineCodeEntity

data class InlineCodeEntityImpl(
    override val offsets: IntRange
) : InlineCodeEntity {
}