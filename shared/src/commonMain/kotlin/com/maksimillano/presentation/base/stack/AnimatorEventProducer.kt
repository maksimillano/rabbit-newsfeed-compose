package com.maksimillano.presentation.base.stack

import com.maksimillano.presentation.base.stack.animator.TransitionEvent

fun interface AnimatorEventProducer<ENTRY : Any> {
    fun sendEvent(transitionEvent: TransitionEvent<ENTRY>)
}