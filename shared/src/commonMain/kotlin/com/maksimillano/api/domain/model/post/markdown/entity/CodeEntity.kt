package com.maksimillano.api.domain.model.post.markdown.entity

interface CodeEntity : MarkdownEntity {
    val language: Language
    val offsets: IntRange

    enum class Language {
        KOTLIN,
        JAVA,
        CPP
    }
}