package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.domain.model.post.image.ImageSizeable

interface ImageSizeCalculator {
    fun calculate(
        thumbnails: List<ImageSizeable>,
        desiredSizesToUrl: MutableList<ImagesRow>
    )

    data class SuitableThumb(val measuredWidth: Int, val measuredHeight: Int, val thumb: ImageSizeable)
    sealed interface ImagesRow {
        val height: Int
    }
    data class SimpleImagesRow(override val height: Int, val images: List<SuitableThumb>) : ImagesRow
    data class GridImagesRow(
        override val height: Int,
        val left: SuitableThumb,
        val topRight: SuitableThumb,
        val bottomRight: SuitableThumb
    ) : ImagesRow
}