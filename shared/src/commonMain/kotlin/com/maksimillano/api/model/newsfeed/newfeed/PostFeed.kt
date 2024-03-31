package com.maksimillano.api.model.newsfeed.newfeed

import com.maksimillano.api.model.attachment.Attachment
import com.maksimillano.api.model.newsfeed.markdown.Markdown

interface PostFeed : Feed {
    val postId: Long
    val ownerId: Long
    val text: String
    val canEdit: Boolean
    val canDelete: Boolean
    val isFavorite: Boolean
    val likesInfo: LikesInfo
    val commentsInfo: CommentsInfo
    val repostsInfo: RepostsInfo
    val viewsInfo: ViewsInfo
    val attachments: List<Attachment>
    val geoInfo: GeoInfo?
    val markdown: Markdown?
    val lastCommentInfo: LastCommentInfo?

    interface CommentsInfo {
        val count: Int
        val canPost: Boolean
        val canView: Boolean
    }

    interface LikesInfo {
        val count: Int
        val isLiked: Boolean
        val canLike: Boolean
    }

    interface RepostsInfo {
        val count: Int
        val isReposted: Boolean
        val canRepost: Boolean
    }

    interface ViewsInfo {
        val count: Int
    }

    interface LastCommentInfo {
        val owner: Long
        val text: String
        val date: Long
        val likesInfo: CommentLikesInfo

        interface CommentLikesInfo {
            val count: Int
            val isLiked: Boolean
            val canLike: Boolean
            val canPublish: Boolean
            val groupLiked: Boolean
        }
    }

    interface GeoInfo {
        val type: Type
        val coordinates: String
        val title: String
        val countryCode: String
        val cityCode: String
        val address: String

        enum class Type {
            PLACE,
            POINT;
        }
    }
}