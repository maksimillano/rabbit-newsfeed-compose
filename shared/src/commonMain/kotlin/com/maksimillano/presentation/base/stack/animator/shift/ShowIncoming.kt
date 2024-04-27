package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.base.BaseComponentOld
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.presentation.base.stack.animator.TransitionEvent
import com.maksimillano.util.pxToDpValue

@Composable
internal fun <ENTRY : Any> ShiftStackAnimator<ENTRY>.showIncoming(
    topComponent: BaseComponentOld,
    bottomComponent: BaseComponentOld,
    animatorEventProducer: AnimatorEventProducer<ENTRY>
) {
    val density = LocalDensity.current
    var alpha by remember { mutableStateOf(0f) }
    var offsetProvider by remember { mutableStateOf(0.dp) }
    val horizontalOffset by animateDpAsState(
        targetValue = offsetProvider,
        animationSpec = tween(
            durationMillis = ShiftStackAnimator.ANIMATION_IN_DURATION,
            easing = ShiftStackAnimator.ANIMATION_APPEAR_EASING
        ),
        finishedListener = {
            animatorEventProducer.sendEvent(TransitionEvent.InEnd(this))
        }
    )
    val offsetInverse = offsetProvider - horizontalOffset

    // TODO make content not clickable while animation
    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val width = it.size.width.pxToDpValue(density)
                offsetProvider = width // / 2
                alpha = 1f
            }
    ) {
        // bottomComponent.render()
        Box(
            Modifier
                .offset(x = offsetInverse)
                .alpha(alpha)
                .fillMaxSize()
        ) {
            // topComponent.render()
        }
    }
}