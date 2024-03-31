package com.maksimillano.presentation.features.launch

import com.maksimillano.api.model.user.EngineUser

sealed interface LaunchState {
    data object Loading : LaunchState
    data class Loaded(val engineUser: EngineUser) : LaunchState
    data class Error(val throwable: Throwable) : LaunchState
}