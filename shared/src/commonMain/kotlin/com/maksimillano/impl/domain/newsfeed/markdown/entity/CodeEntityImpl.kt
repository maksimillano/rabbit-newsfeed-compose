package com.maksimillano.impl.domain.newsfeed.markdown.entity

import com.maksimillano.api.domain.model.post.markdown.entity.CodeEntity

data class CodeEntityImpl(
    override val language: CodeEntity.Language,
    override val offsets: IntRange
) : CodeEntity {
}