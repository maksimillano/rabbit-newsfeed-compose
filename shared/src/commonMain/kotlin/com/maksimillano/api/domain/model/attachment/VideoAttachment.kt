package com.maksimillano.api.domain.model.attachment

import com.maksimillano.api.domain.model.post.image.ImageSizeable

interface VideoAttachment : Attachment {
    val id: Int
    val ownerId: Int
    val date: Long
    val title: String
    val description: String
    val duration: Int
    val videoType: VideoType
    val image: ImageSizeable
    val views: Int
    val player: String
    val width: Int
    val height: Int

    enum class VideoType {
        VIDEO,
        MUSIC_VIDEO,
        MOVIE,
        SHORT_VIDEO
    }
}