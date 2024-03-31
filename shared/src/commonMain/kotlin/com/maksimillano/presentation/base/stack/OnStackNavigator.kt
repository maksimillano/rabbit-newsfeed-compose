package com.maksimillano.presentation.base.stack

import com.maksimillano.presentation.base.stack.animator.StackEvent

fun interface OnStackNavigator<ENTRY : Any> {
    fun sendEvent(stackEvent: StackEvent<ENTRY>)
}