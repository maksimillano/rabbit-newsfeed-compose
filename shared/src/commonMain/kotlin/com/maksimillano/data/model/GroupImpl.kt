package com.maksimillano.data.model

import com.maksimillano.api.model.Group

data class GroupImpl(
    override val groupId: Long,
    override val name: String,
    override val shortName: String,
    override val isAdmin: Boolean,
    override val isMember: Boolean,
    override val photos: List<String>,
    override val adminLevel: Group.AdminLevel,
    override val deactivatedStatus: Group.DeactivatedStatus,
    override val accessState: Group.AccessState
) : Group