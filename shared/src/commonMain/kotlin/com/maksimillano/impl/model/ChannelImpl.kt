package com.maksimillano.impl.model

import com.maksimillano.api.model.account.Peer
import com.maksimillano.api.model.post.image.ImageSizeable
import com.maksimillano.api.model.profile.Channel

data class ChannelImpl(
    override val peer: Peer,
    override val name: String,
    override val shortName: String = "",
    override val about: String = "",
    override val avatar: ImageSizeable,
    override val photos: List<ImageSizeable>,
    override val isAdmin: Boolean = false,
    override val isMember: Boolean = false,
    override val isOwner: Boolean = false,
    override val adminLevel: Channel.AdminLevel? = null,
    override val deactivatedStatus: Channel.DeactivatedStatus? = null,
    override val accessState: Channel.AccessState = Channel.AccessState.OPEN,
) : Channel