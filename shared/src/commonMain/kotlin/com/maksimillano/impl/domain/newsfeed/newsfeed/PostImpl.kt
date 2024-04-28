package com.maksimillano.impl.domain.newsfeed.newsfeed

import com.maksimillano.api.domain.model.account.Peer
import com.maksimillano.api.domain.model.attachment.Attachment
import com.maksimillano.api.domain.model.post.markdown.Markdown
import com.maksimillano.api.domain.model.post.Post

data class PostImpl(
    override val id: String,
    override val sourceId: Long,
    override val date: Long,

    override val postId: Long,
    override val owner: Peer,
    override val text: String,
    override val canEdit: Boolean = false,
    override val canDelete: Boolean = false,
    override val isFavorite: Boolean = false,
    override val likesInfo: Post.LikesInfo = LikesInfoImpl(),
    override val commentsInfo: Post.CommentsInfo = CommentsInfoImpl(),
    override val repostsInfo: Post.RepostsInfo = RepostsInfoImpl(),
    override val viewsInfo: Post.ViewsInfo = ViewsInfoImpl(),
    override val attachments: List<Attachment> = emptyList(),
    override val geoInfo: Post.GeoInfo? = null,
    override val markdown: Markdown? = null,
    override val lastCommentInfo: Post.LastCommentInfo? = null
) : Post {

    data class CommentsInfoImpl(
        override val count: Int = 0,
        override val canPost: Boolean = false,
        override val canView: Boolean = false,
    ) : Post.CommentsInfo

    data class LikesInfoImpl(
        override val count: Int = 0,
        override val isLiked: Boolean = false,
        override val canLike: Boolean = false,
    ) : Post.LikesInfo

    data class RepostsInfoImpl(
        override val count: Int = 0,
        override val isReposted: Boolean = false,
        override val canRepost: Boolean = false,
    ) : Post.RepostsInfo

    data class ViewsInfoImpl(
        override val count: Int = 0
    ) : Post.ViewsInfo

    data class LastCommentInfoImpl(
        override val owner: Long,
        override val text: String,
        override val date: Long,
        override val likesInfo: Post.LastCommentInfo.CommentLikesInfo
    ) : Post.LastCommentInfo {
        data class CommentLikesInfoImpl(
            override val count: Int,
            override val isLiked: Boolean,
            override val canLike: Boolean,
            override val canPublish: Boolean,
            override val groupLiked: Boolean
        ) : Post.LastCommentInfo.CommentLikesInfo
    }

    data class GeoInfoImpl(
        override val type: Post.GeoInfo.Type,
        override val coordinates: String,
        override val title: String,
        override val countryCode: String,
        override val cityCode: String,
        override val address: String
    ) : Post.GeoInfo
}