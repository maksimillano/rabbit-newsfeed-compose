package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.ui.unit.Dp

sealed interface ShiftingOutState {
    data object Animating : ShiftingOutState
    data object Dragging : ShiftingOutState
    data class TouchUp(val offset: Dp) : ShiftingOutState
}
