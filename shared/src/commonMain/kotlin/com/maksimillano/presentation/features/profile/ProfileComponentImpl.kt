package com.maksimillano.presentation.features.profile

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper

class ProfileComponentImpl(contextWrapper: ContextWrapper) : ProfileComponent(contextWrapper) {
    override val viewModel: ProfileViewModel
        get() = TODO("Not yet implemented")

    override fun createRouter(): MviRouter<ProfileNavEvent> = MviRouter {
    }

    @Composable
    override fun render() = ProfileScreen(this)
}