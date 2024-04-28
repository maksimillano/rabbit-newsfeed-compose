package com.maksimillano.impl.domain.newsfeed.post.image

import com.maksimillano.api.domain.model.post.image.ImageSizeable

class ImageSizeableImpl(
    override val width: Int,
    override val height: Int,
    override val url: String
) : ImageSizeable {
    override fun urlBestQuality(width: Int): String {
        return url
    }
}