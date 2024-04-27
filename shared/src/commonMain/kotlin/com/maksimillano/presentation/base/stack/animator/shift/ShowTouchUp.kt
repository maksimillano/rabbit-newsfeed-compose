package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.base.BaseComponentOld
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.presentation.base.stack.animator.TransitionEvent
import com.maksimillano.util.pxToDpValue

@Composable
internal fun <ENTRY : Any> ShiftStackAnimator<ENTRY>.showTouchUp(
    topComponent: BaseComponentOld,
    bottomComponent: BaseComponentOld,
    touchUpState: ShiftingOutState.TouchUp,
    animatorEventProducer: AnimatorEventProducer<ENTRY>,
    shiftStateCallback: (ShiftingOutState) -> Unit // Finger up -> animate reset or animate pop
) {
    val density = LocalDensity.current
    var isCancelling by remember { mutableStateOf(false) }
    var offsetProvider by remember { mutableStateOf(touchUpState.offset) }
    val horizontalOffset by animateDpAsState(
        targetValue = offsetProvider,
        animationSpec = tween(
            durationMillis = ShiftStackAnimator.ANIMATION_OUT_DURATION,
            easing = LinearEasing
        ),
        finishedListener = {
            if (isCancelling) {
                animatorEventProducer.sendEvent(TransitionEvent.Cancelled(this))
            } else {
                animatorEventProducer.sendEvent(TransitionEvent.OutEnd(this))
            }
        }
    )

    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val width = it.size.width
                val widthDp = width.pxToDpValue(density)

                offsetProvider = if (touchUpState.offset > widthDp / 2) {
                    isCancelling = false
                    widthDp
                } else {
                    isCancelling = true
                    0.dp
                }
            }
    ) {
        // bottomComponent.render()

        Box(
            Modifier
                .offset(x = horizontalOffset)
                .fillMaxSize()
        ) {
            // topComponent.render()
        }
    }
}