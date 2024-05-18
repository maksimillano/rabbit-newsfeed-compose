package com.maksimillano.presentation.base.mvi

import com.maksimillano.presentation.base.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MviViewModel<STATE: MviState, EVENT: MviEvent, NAVIGATION: MviNavigationEvent, VIEW_ACTION: MviViewAction> : ViewModel() {
    abstract val state: StateFlow<STATE>
    abstract val navigationEvents: SharedFlow<NAVIGATION>
    abstract val viewActions: SharedFlow<VIEW_ACTION>
    abstract fun onNewEvent(event: EVENT)
}