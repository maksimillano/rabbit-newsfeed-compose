package com.maksimillano.api.model.attachment

interface DocumentAttachment : Attachment {
    val id: Long
    val ownerId: Long
    val date: Long
    val title: String
    val size: Long
    val extension: String
    val url: String
    val type: Type
    val previewInfo: PreviewInfo?

    enum class Type {
        TXT,
        ARCHIVES,
        GIF,
        IMAGE,
        AUDIO,
        VIDEO,
        EBOOK,
        UNKNOWN
    }

    sealed interface PreviewInfo

    data class Photo(
        val sizes: List<PreviewThumbnail>? = null
    ) : PreviewInfo {
        data class PreviewThumbnail(
            val url: String = "",
            val width: Int = 0,
            val height: Int = 0,
            val type: DocumentAttachment.Type? = null
        )

        enum class Type {
            /** Пропорциональная копия изображения с максимальной стороной 100px; */
            S,

            /** Пропорциональная копия изображения с максимальной стороной 130px; */
            M,

            /** Пропорциональная копия изображения с максимальной стороной 604px; */
            X,

            /** Пропорциональная копия изображения с максимальной стороной 807px; */
            Y,

            /** Пропорциональная копия изображения с максимальным размером 1080x1024px; */
            Z,

            /** Копия изображения с размерами оригинала. */
            O
        }
    }

    data class Graffiti(
        val src: String = "",
        val width: Int = 0,
        val height: Int = 0
    ) : PreviewInfo

    data class AudioMessage(
        val duration: Long = 0L,
        val waveform: List<Int> = emptyList(),
        val linkOgg: String = "",
        val linkMp3: String = ""
    ) : PreviewInfo
}