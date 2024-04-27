package com.maksimillano.presentation.base.mvi

import com.maksimillano.presentation.base.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<STATE: MviState, EVENT: MviEvent, NAVIGATION: MviNavigationEvent, VIEW_ACTION: MviViewAction> : ViewModel {
    val state: StateFlow<STATE>
    val navigationEvents: SharedFlow<NAVIGATION>
    val viewActions: SharedFlow<VIEW_ACTION>
    fun onNewEvent(event: EVENT)
}