package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.Child
import com.maksimillano.presentation.base.BaseComponentOld
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.presentation.base.stack.animator.StackAnimator
import com.maksimillano.presentation.base.stack.animator.StackState

class ShiftStackAnimator<ENTRY : Any>(
    private val draggingEnabled: Boolean = true
) : StackAnimator<ENTRY> {
    @Composable
    override fun renderStack(
        stackState: StackState<ENTRY>,
        stack: List<Child.Created<ENTRY, BaseComponentOld>>,
        animatorEventProducer: AnimatorEventProducer<ENTRY>
    ) {

        val topComponent = stack.last().instance

        if (stack.size == 1) {
            showSettled(topComponent, animatorEventProducer) {
            }
            return
        }

        val bottomComponent = stack[stack.size - 2].instance
        var shiftingState: ShiftingOutState by remember { mutableStateOf(ShiftingOutState.Animating) }

        when (stackState) {
            is StackState.Settled -> {
                showSettled(topComponent, animatorEventProducer) {
                    shiftingState = it
                }
            }
            is StackState.In -> {
                showIncoming(topComponent, bottomComponent, animatorEventProducer)
            }
            is StackState.Out -> {
                if (draggingEnabled) {
                    when (val state = shiftingState) {
                        ShiftingOutState.Animating -> {
                            showOutgoing(topComponent, bottomComponent, animatorEventProducer)
                        }
                        ShiftingOutState.Dragging -> {
                            showDragging(topComponent, bottomComponent, animatorEventProducer) {
                                shiftingState = it
                            }
                        }
                        is ShiftingOutState.TouchUp -> {
                            showTouchUp(topComponent, bottomComponent, state, animatorEventProducer) {
                                shiftingState = it
                            }
                        }
                    }
                } else {
                    showOutgoing(topComponent, bottomComponent, animatorEventProducer)
                }
            }
        }
    }

    companion object {
        const val ANIMATION_IN_DURATION = 150
        const val ANIMATION_OUT_DURATION = 100
        val ANIMATION_APPEAR_EASING = LinearOutSlowInEasing
        const val START_ALPHA = 0.0f
        val shadowElevation = 50.dp
    }
}