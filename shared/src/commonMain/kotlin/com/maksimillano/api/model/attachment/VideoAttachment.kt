package com.maksimillano.api.model.attachment

interface VideoAttachment : Attachment {
    val id: Int
    val ownerId: Int
    val date: Long
    val title: String
    val description: String
    val duration: Int
    val type: Type
    val image: List<FramePhoto>
    val firstFrame: List<FramePhoto>
    val views: Int
    val player: String
    val width: Int
    val height: Int

    data class FramePhoto(
        val url: String,
        val height: Int,
        val width: Int
    )

    enum class Type {
        VIDEO,
        MUSIC_VIDEO,
        MOVIE,
        SHORT_VIDEO
    }
}