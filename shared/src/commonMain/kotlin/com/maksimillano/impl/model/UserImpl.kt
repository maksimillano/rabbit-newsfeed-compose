package com.maksimillano.impl.model

import com.maksimillano.api.model.account.Peer
import com.maksimillano.api.model.post.image.ImageSizeable
import com.maksimillano.api.model.profile.User

data class UserImpl(
    override val peer: Peer,
    override val firstName: String,
    override val lastName: String,
    override val nickname: String,
    override val about: String = "",
    override val avatar: ImageSizeable,
    override val photos: List<ImageSizeable>,
    override val birthdayDate: String = "",
    override val sex: User.Sex = User.Sex.UNKNOWN
) : User {
    override val name: String
        get() = "$firstName $lastName"
}