package com.maksimillano.api.model.post.image

interface ImageSizeable : Image {
    fun urlBestQuality(width: Int): String
}