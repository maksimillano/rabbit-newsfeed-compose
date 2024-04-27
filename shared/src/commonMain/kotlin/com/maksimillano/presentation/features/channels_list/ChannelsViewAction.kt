package com.maksimillano.presentation.features.channels_list

import com.maksimillano.presentation.base.mvi.MviViewAction

sealed interface ChannelsViewAction : MviViewAction {
    data object ShowToast : ChannelsViewAction
}