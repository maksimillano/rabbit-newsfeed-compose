package com.maksimillano.presentation.base.mvi

fun interface MviRouter<N: MviNavigationEvent> {
    fun onHandleNavigationEvent(event: N)
}