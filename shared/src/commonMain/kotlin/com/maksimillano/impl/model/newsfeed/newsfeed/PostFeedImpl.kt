package com.maksimillano.impl.model.newsfeed.newsfeed

import com.maksimillano.api.model.attachment.Attachment
import com.maksimillano.api.model.post.markdown.Markdown
import com.maksimillano.api.model.post.newfeed.PostFeed

data class PostFeedImpl(
    override val id: String,
    override val sourceId: Long,
    override val date: Long,

    override val postId: Long,
    override val ownerId: Long,
    override val text: String,
    override val canEdit: Boolean = false,
    override val canDelete: Boolean = false,
    override val isFavorite: Boolean = false,
    override val likesInfo: PostFeed.LikesInfo = LikesInfoImpl(),
    override val commentsInfo: PostFeed.CommentsInfo = CommentsInfoImpl(),
    override val repostsInfo: PostFeed.RepostsInfo = RepostsInfoImpl(),
    override val viewsInfo: PostFeed.ViewsInfo = ViewsInfoImpl(),
    override val attachments: List<Attachment> = emptyList(),
    override val geoInfo: PostFeed.GeoInfo? = null,
    override val markdown: Markdown? = null,
    override val lastCommentInfo: PostFeed.LastCommentInfo? = null
) : PostFeed {

    data class CommentsInfoImpl(
        override val count: Int = 0,
        override val canPost: Boolean = false,
        override val canView: Boolean = false,
    ) : PostFeed.CommentsInfo

    data class LikesInfoImpl(
        override val count: Int = 0,
        override val isLiked: Boolean = false,
        override val canLike: Boolean = false,
    ) : PostFeed.LikesInfo

    data class RepostsInfoImpl(
        override val count: Int = 0,
        override val isReposted: Boolean = false,
        override val canRepost: Boolean = false,
    ) : PostFeed.RepostsInfo

    data class ViewsInfoImpl(
        override val count: Int = 0
    ) : PostFeed.ViewsInfo

    data class LastCommentInfoImpl(
        override val owner: Long,
        override val text: String,
        override val date: Long,
        override val likesInfo: PostFeed.LastCommentInfo.CommentLikesInfo
    ) : PostFeed.LastCommentInfo {
        data class CommentLikesInfoImpl(
            override val count: Int,
            override val isLiked: Boolean,
            override val canLike: Boolean,
            override val canPublish: Boolean,
            override val groupLiked: Boolean
        ) : PostFeed.LastCommentInfo.CommentLikesInfo
    }

    data class GeoInfoImpl(
        override val type: PostFeed.GeoInfo.Type,
        override val coordinates: String,
        override val title: String,
        override val countryCode: String,
        override val cityCode: String,
        override val address: String
    ) : PostFeed.GeoInfo
}