package com.maksimillano.presentation.features.channels_list

import androidx.compose.runtime.Composable
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper

class ChannelsComponentImpl(contextWrapper: ContextWrapper) : ChannelsComponent(contextWrapper) {
    override val viewModel: ChannelsViewModel
        get() = TODO("Not yet implemented")

    override fun createRouter(): MviRouter<ChannelsNavEvent> {
        return ChannelsRouter(appNavigator = navigator)
    }

    @Composable
    override fun render() = ChannelsScreen(this)
}