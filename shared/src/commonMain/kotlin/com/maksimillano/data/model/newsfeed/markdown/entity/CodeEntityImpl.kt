package com.maksimillano.data.model.newsfeed.markdown.entity

import com.maksimillano.api.model.newsfeed.markdown.entity.CodeEntity

data class CodeEntityImpl(
    override val language: CodeEntity.Language,
    override val offsets: IntRange
) : CodeEntity {
}