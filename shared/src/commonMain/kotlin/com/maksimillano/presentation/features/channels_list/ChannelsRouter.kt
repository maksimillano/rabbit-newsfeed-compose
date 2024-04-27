package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.AppNavigator

class ChannelsRouter(private val appNavigator: AppNavigator) : MviRouter<ChannelsNavEvent> {
    override fun onHandleNavigationEvent(event: ChannelsNavEvent) {
    }
}