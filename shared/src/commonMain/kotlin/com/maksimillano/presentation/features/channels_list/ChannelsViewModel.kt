package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class ChannelsViewModel(initState: ChannelsState): BaseMviViewModel<ChannelsState, ChannelsMviEvent, ChannelsNavEvent, ChannelsViewAction>(initState)