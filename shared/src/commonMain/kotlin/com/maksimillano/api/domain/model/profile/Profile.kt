package com.maksimillano.api.domain.model.profile

import com.maksimillano.api.domain.model.account.Peer
import com.maksimillano.api.domain.model.post.image.ImageSizeable

sealed interface Profile {
    val peer: Peer
    val name: String
    val about: String
    val avatar: ImageSizeable
    val photos: List<ImageSizeable>
}