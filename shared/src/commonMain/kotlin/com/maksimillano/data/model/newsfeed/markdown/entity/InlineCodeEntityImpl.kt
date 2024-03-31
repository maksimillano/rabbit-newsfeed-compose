package com.maksimillano.data.model.newsfeed.markdown.entity

import com.maksimillano.api.model.newsfeed.markdown.entity.InlineCodeEntity

data class InlineCodeEntityImpl(
    override val offsets: IntRange
) : InlineCodeEntity {
}