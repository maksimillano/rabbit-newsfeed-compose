package com.maksimillano.api.domain.model.post.image

interface ImageSizeable : Image {
    fun urlBestQuality(width: Int): String
}