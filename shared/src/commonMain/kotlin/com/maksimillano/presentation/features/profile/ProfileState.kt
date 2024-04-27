package com.maksimillano.presentation.features.profile

import com.maksimillano.presentation.base.mvi.MviState

data class ProfileState(
    val avatar: String = "",
    val title: String = "",
    val subtitle: String = "",
    val about: String = "",
    val link: String = ""
) : MviState