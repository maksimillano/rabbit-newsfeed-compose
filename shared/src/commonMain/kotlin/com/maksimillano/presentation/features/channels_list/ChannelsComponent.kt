package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class ChannelsComponent(contextWrapper: ContextWrapper) : BaseMviComponent<ChannelsState, ChannelsMviEvent, ChannelsNavEvent, ChannelsViewAction>(contextWrapper)