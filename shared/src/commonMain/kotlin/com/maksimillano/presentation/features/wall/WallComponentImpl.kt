package com.maksimillano.presentation.features.wall

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper

class WallComponentImpl(contextWrapper: ContextWrapper) : WallComponent(contextWrapper) {
    override val viewModel: WallViewModel
        get() = TODO("Not yet implemented")

    override fun createRouter(): MviRouter<WallNavEvent> = MviRouter {
    }

    @Composable
    override fun render() = WallScreen(this)
}