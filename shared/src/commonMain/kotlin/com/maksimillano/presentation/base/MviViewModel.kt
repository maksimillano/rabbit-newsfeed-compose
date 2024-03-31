package com.maksimillano.presentation.base

import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<STATE, EVENT> : ViewModel {
    val state: StateFlow<STATE>
    fun postEvent(event: EVENT)
}