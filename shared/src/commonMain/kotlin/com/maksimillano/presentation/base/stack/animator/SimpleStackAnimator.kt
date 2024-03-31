package com.maksimillano.presentation.base.stack.animator

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.Child
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.stack.AnimatorEventProducer

class SimpleStackAnimator<ENTRY : Any> : StackAnimator<ENTRY> {
    @Composable
    override fun renderStack(
        stackState: StackState<ENTRY>,
        stack: List<Child.Created<ENTRY, BaseComponent>>,
        animatorEventProducer: AnimatorEventProducer<ENTRY>
    ) {

        val lastElement = stack.last()
        val component = lastElement.instance

        when (stackState) {
            is StackState.In -> {
                animatorEventProducer.sendEvent(TransitionEvent.InEnd(this))
                // component.render()
            }
            is StackState.Out -> {
                animatorEventProducer.sendEvent(TransitionEvent.OutEnd(this))
                // component.render()
            }
            is StackState.Settled -> {
                // component.render()
            }
        }
    }
}