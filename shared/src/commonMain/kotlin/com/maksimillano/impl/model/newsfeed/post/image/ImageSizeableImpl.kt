package com.maksimillano.impl.model.newsfeed.post.image

import com.maksimillano.api.model.post.image.ImageSizeable

class ImageSizeableImpl(
    override val width: Int,
    override val height: Int,
    override val url: String
) : ImageSizeable {
    override fun urlBestQuality(width: Int): String {
        return url
    }
}