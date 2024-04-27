package com.maksimillano.api.model.post.image

interface Image {
    val width: Int
    val height: Int
    val url: String

    val ratio: Float
        get() = width.toFloat() / height
}