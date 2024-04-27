package com.maksimillano.presentation.base.mvi

interface MviComponent<STATE : MviState, EVENT : MviEvent, NAVIGATION: MviNavigationEvent, VIEW_ACTION: MviViewAction> {
    val viewModel: MviViewModel<STATE, EVENT, NAVIGATION, VIEW_ACTION>
    fun createRouter(): MviRouter<NAVIGATION>
}