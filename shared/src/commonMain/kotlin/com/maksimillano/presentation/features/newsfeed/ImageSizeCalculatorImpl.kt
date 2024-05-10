package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.domain.model.post.image.ImageSizeable
import kotlin.math.sqrt

class ImageSizeCalculatorImpl(private val photoMaxWidthProvider: () -> Int) : ImageSizeCalculator {
    private val MAX_RATIO = 1.5F
    private val MIN_RATIO = 0.66F

    override fun process(
        thumbnails: List<ImageSizeable>,
        desiredSizesToUrl: MutableList<ImageSizeCalculator.ImagesRow>
    ) {
        val thumbSize = thumbnails.size
        if (thumbSize > 10 && thumbnails.isEmpty()) {
            throw IllegalArgumentException("Illegal count of thumbnails ${thumbSize}")
        }

        val maxWidth = photoMaxWidthProvider()
        val maxHeight = (MAX_RATIO * maxWidth).toInt()
        val squareHeight = maxWidth
        val minHeight = (MIN_RATIO * maxWidth).toInt()

        when (thumbSize) {
            1 -> {
                val first = thumbnails.first()
                val desiredRatio = first.ratio.coerceIn(MIN_RATIO, MAX_RATIO)
                val height = (maxWidth / desiredRatio).toInt()
                desiredSizesToUrl.add(
                    ImageSizeCalculator.SimpleImagesRow(
                        height,
                        listOf(ImageSizeCalculator.SuitableThumb(maxWidth, height, first))
                    )
                )
            }
            2 -> {
                val firstPhoto = thumbnails.first()
                val secondPhoto = thumbnails.last()

                val percentage = (firstPhoto.ratio / (firstPhoto.ratio + secondPhoto.ratio)).coerceIn(0.25f, 0.75f)
                val widthFirst = (maxWidth * percentage).toInt()
                val widthSecond = (maxWidth * (1 - percentage)).toInt()

                val desiredHeightForAll = (widthFirst / firstPhoto.ratio).toInt()
                val desiredHeightSecond = widthSecond / secondPhoto.ratio

                val isMinDesiredHeightIsGreaterMin = desiredHeightForAll >= minHeight
                val isMinDesiredHeightIsLessMax = desiredHeightForAll <= maxHeight
                val realHeight: Int = if (isMinDesiredHeightIsGreaterMin && isMinDesiredHeightIsLessMax) {
                    desiredHeightForAll
                } else {
                    if (!isMinDesiredHeightIsLessMax) {
                        // los dos son mas altos que `maxHeight`
                        maxHeight
                    } else {
                        // usamos que lo menos altura `minHeight`
                        minHeight
                    }
                }

                desiredSizesToUrl.add(
                    ImageSizeCalculator.SimpleImagesRow(
                        realHeight,
                        listOf(
                            ImageSizeCalculator.SuitableThumb(widthFirst, realHeight, firstPhoto),
                            ImageSizeCalculator.SuitableThumb(widthSecond, realHeight, secondPhoto),
                        )
                    )
                )
            }
            3 -> {
                // 1 - is vertical or square: 66% 33%
                // 1 - is horizontal: 75% 25%
                val firstPhoto = thumbnails.first()
                val secondPhoto = thumbnails.last()
                val thirdPhoto = thumbnails.last()

                desiredSizesToUrl.add(
                    ImageSizeCalculator.GridImagesRow(
                        squareHeight,
                        ImageSizeCalculator.SuitableThumb((maxWidth * 0.66f).toInt(), squareHeight, firstPhoto),
                        ImageSizeCalculator.SuitableThumb((maxWidth * 0.33f).toInt(), squareHeight / 2, secondPhoto),
                        ImageSizeCalculator.SuitableThumb((maxWidth * 0.33f).toInt(), squareHeight / 2, thirdPhoto)
                    )
                )
            }
            4, 9 -> {
                // square
                val rowSize = sqrt(thumbSize.toFloat()).toInt()

                val thumbWidth = maxWidth / rowSize
                val elements = thumbnails.map {
                    ImageSizeCalculator.SuitableThumb(thumbWidth, thumbWidth, it)
                }
                elements.chunked(rowSize).forEach { imagesInRow ->
                    desiredSizesToUrl.add(ImageSizeCalculator.SimpleImagesRow(thumbWidth, imagesInRow))
                }
            }
            6 -> {
                val lowestHeight = thumbnails.maxOf { it.ratio }
                    .coerceIn(MIN_RATIO, MAX_RATIO)
                val thumbWidth = maxWidth / 3
                val thumbHeight = (thumbWidth / lowestHeight).toInt()

                val elements = thumbnails.map {
                    ImageSizeCalculator.SuitableThumb(thumbWidth, thumbHeight, it)
                }.chunked(3)

                elements.forEach {
                    desiredSizesToUrl.add(ImageSizeCalculator.SimpleImagesRow(thumbHeight, it))
                }
            }
            5, 7, 8, 10 -> {
                // 50% 50%
                // 33% 33% 33%

                val topThumbs = thumbnails.subList(0, 2)
                val topLowestHeight = topThumbs.maxOf { it.ratio }
                    .coerceIn(MIN_RATIO, MAX_RATIO)
                val topWidth = maxWidth / 2
                val topHeight = (topWidth / topLowestHeight).toInt()
                val tops = topThumbs.map {
                    ImageSizeCalculator.SuitableThumb(topWidth, topHeight, it)
                }

                val bottomThumbs = thumbnails.subList(2, thumbSize)
                val bottomLowestHeight = bottomThumbs.maxOf { it.ratio }
                    .coerceIn(MIN_RATIO, MAX_RATIO)
                val bottomWidth = maxWidth / bottomThumbs.size
                val bottomHeight = (bottomWidth / bottomLowestHeight).toInt()
                val bottoms = bottomThumbs.map {
                    ImageSizeCalculator.SuitableThumb(bottomWidth, bottomHeight, it)
                }

                desiredSizesToUrl.add(ImageSizeCalculator.SimpleImagesRow(topHeight, tops))
                desiredSizesToUrl.add(ImageSizeCalculator.SimpleImagesRow(bottomHeight, bottoms))
            }
        }
    }
}