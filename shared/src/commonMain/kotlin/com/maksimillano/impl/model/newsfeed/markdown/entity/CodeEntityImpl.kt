package com.maksimillano.impl.model.newsfeed.markdown.entity

import com.maksimillano.api.model.post.markdown.entity.CodeEntity

data class CodeEntityImpl(
    override val language: CodeEntity.Language,
    override val offsets: IntRange
) : CodeEntity {
}