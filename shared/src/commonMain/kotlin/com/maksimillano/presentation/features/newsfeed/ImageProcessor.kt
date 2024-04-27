package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.model.attachment.PhotoAttachment
import com.maksimillano.api.model.post.image.ImageSizeable
import kotlin.math.min

object ImageProcessor {
    private const val MAX_RATIO = 1.5

//    fun process(
//        thumbnails: List<ImageSizeable>,
//        originalWidth: Int,
//        desiredSizesToUrl: MutableList<SuitableThumb>
//    ) {
//
//        if (thumbnails.size > 10 && thumbnails.isEmpty()) {
//            throw IllegalArgumentException("Illegal count of thumbnails")
//        }
//
//        val photoThumbnail = thumbnails.first()
//        val desiredSize = Size(originalWidth, originalWidth)
//        val thumbnailFound = findMostSuitable(photoThumbnail, desiredSize)
//        desiredSizesToUrl.add(SuitableThumb(desiredSize, thumbnailFound))
//        return // TODO
//
//        when (thumbnails.size) {
//            1 -> {
//
//            }
//            2 -> {
//                // two same: 50% 50% (vert + vert) (sq + sq) (hor + hor)
//                // TODO
//
//                val first = thumbnails.first()
//                val firstRatio = first.first().let { it.width / it.height }
//
//                val last = thumbnails.last()
//                val lastRatio = last.first().let { it.width / it.height }
//
//                val halfWidth = originalWidth / 2
//
//                val desiredHeightFirst = min(halfWidth / firstRatio, (halfWidth / MAX_RATIO).toInt())
//                val desiredHeightLast = min(halfWidth / lastRatio, (halfWidth / MAX_RATIO).toInt())
//            }
//            3 -> {
//                // 1 - is vertical or square:   66% 33%
//                // 1 - is horizontal: 75% 25%
//            }
//            4, 6, 9 -> {
//                // 50% 50%
//                // 50% 50%
//            }
//            5, 7, 8, 10 -> {
//                // 50% 50%
//                // 33% 33% 33%
//            }
//        }
//    }

//    private fun findMostSuitable(thumbnail: List<PhotoAttachment.PhotoThumbnail>, desiredSize: Size): PhotoAttachment.PhotoThumbnail {
//        return thumbnail.find { size ->
//            size.width >= desiredSize.width && size.height >= desiredSize.height
//        } ?: thumbnail.last()
//    }
//
//    data class Size(val width: Int, val height: Int)
//    data class SuitableThumb(val size: Size, val thumb: PhotoAttachment.PhotoThumbnail)
}