package com.maksimillano.data.model.newsfeed.newsfeed

import com.maksimillano.api.model.attachment.Attachment
import com.maksimillano.api.model.newsfeed.markdown.Markdown
import com.maksimillano.api.model.newsfeed.newfeed.PostFeed

data class PostFeedImpl(
    override val id: String,
    override val sourceId: Long,
    override val date: Long,
    override val weight: Long,

    override val postId: Long,
    override val ownerId: Long,
    override val text: String,
    override val canEdit: Boolean,
    override val canDelete: Boolean,
    override val isFavorite: Boolean,
    override val likesInfo: PostFeed.LikesInfo,
    override val commentsInfo: PostFeed.CommentsInfo,
    override val repostsInfo: PostFeed.RepostsInfo,
    override val viewsInfo: PostFeed.ViewsInfo,
    override val attachments: List<Attachment>,
    override val geoInfo: PostFeed.GeoInfo?,
    override val markdown: Markdown?,
    override val lastCommentInfo: PostFeed.LastCommentInfo?
) : PostFeed {

    data class CommentsInfoImpl(
        override val count: Int,
        override val canPost: Boolean,
        override val canView: Boolean
    ) : PostFeed.CommentsInfo

    data class LikesInfoImpl(
        override val count: Int,
        override val isLiked: Boolean,
        override val canLike: Boolean
    ) : PostFeed.LikesInfo

    data class RepostsInfoImpl(
        override val count: Int,
        override val isReposted: Boolean,
        override val canRepost: Boolean
    ) : PostFeed.RepostsInfo

    data class ViewsInfoImpl(
        override val count: Int
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