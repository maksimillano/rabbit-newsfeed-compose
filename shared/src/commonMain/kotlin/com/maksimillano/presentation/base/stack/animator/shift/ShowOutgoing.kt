package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.presentation.base.stack.animator.TransitionEvent
import com.maksimillano.util.pxToDpValue

@Composable
internal fun <ENTRY : Any> ShiftStackAnimator<ENTRY>.showOutgoing(
    topComponent: BaseComponent,
    bottomComponent: BaseComponent,
    animatorEventProducer: AnimatorEventProducer<ENTRY>
) {
    val density = LocalDensity.current
    var offsetProvider by remember { mutableStateOf(0.dp) }
    val horizontalOffset by animateDpAsState(
        targetValue = offsetProvider,
        animationSpec = tween(
            durationMillis = ShiftStackAnimator.ANIMATION_OUT_DURATION,
            easing = LinearEasing
        ),
        finishedListener = {
            animatorEventProducer.sendEvent(TransitionEvent.OutEnd(this))
        }
    )

    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val width = it.size.width
                offsetProvider = width.pxToDpValue(density) // / 2
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
