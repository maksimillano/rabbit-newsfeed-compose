package com.maksimillano.api.model.newsfeed.markdown.entity

interface CodeEntity : MarkdownEntity {
    val language: Language
    val offsets: IntRange

    enum class Language {
        KOTLIN,
        JAVA,
        CPP
    }
}