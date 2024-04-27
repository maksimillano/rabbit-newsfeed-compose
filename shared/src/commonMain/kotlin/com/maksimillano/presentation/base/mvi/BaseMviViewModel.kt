package com.maksimillano.presentation.base.mvi

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseMviViewModel<STATE : MviState, EVENT : MviEvent, NAVIGATION : MviNavigationEvent, VIEW_ACTION : MviViewAction>(
    private val initialState: STATE
) : MviViewModel<STATE, EVENT, NAVIGATION, VIEW_ACTION> {
    private val _navigationEvents: MutableSharedFlow<NAVIGATION> = MutableSharedFlow()
    private val _viewActions: MutableSharedFlow<VIEW_ACTION> = MutableSharedFlow()
    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    private val pipeFlow = MutableSharedFlow<(STATE) -> STATE>()

    final override val navigationEvents: SharedFlow<NAVIGATION>
        get() = _navigationEvents
    final override val viewActions: SharedFlow<VIEW_ACTION>
        get() = _viewActions
    final override val state: StateFlow<STATE>
        get() = _state

    init {
        launch {
            pipeFlow.map { action -> action(getValue()) }
                .collect { newState ->
                    _state.emit(newState)
                }
        }
    }

    protected fun withState(action: (STATE) -> STATE) {
        launch {
            pipeFlow.emit(action)
        }
    }

    protected fun postNavigationEvent(navigationEvent: NAVIGATION) {
        launch {
            _navigationEvents.emit(navigationEvent)
        }
    }

    protected fun postViewAction(action: VIEW_ACTION) {
        launch {
            _viewActions.emit(action)
        }
    }

    protected fun getValue(): STATE {
        return _state.value
    }
}