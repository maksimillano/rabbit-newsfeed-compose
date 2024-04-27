package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.base.BaseComponentOld
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.presentation.base.stack.animator.TransitionEvent
import com.maksimillano.util.pxToDpValue

@Composable
internal fun <ENTRY : Any> ShiftStackAnimator<ENTRY>.showSettled(
    topComponent: BaseComponentOld,
    animatorEventProducer: AnimatorEventProducer<ENTRY>,
    shiftStateCallback: (ShiftingOutState) -> Unit
) {
    var catch by remember { mutableStateOf(false) }
    var firstTouch by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Main)
                        val notConsumed = event.changes.all { !it.isConsumed }

                        when (event.type) {
                            PointerEventType.Move -> {
                                val xStart = event.changes.first().position.x.pxToDpValue(density)
                                if (!catch && xStart < 50.dp) {
                                    catch = true
                                    firstTouch = xStart
                                    event.changes.forEach { it.consume() }
                                }

                                if (catch) {
                                    if (xStart - firstTouch > 5.dp) {
                                        shiftStateCallback(ShiftingOutState.Dragging)
                                        animatorEventProducer.sendEvent(TransitionEvent.OutBegin(this@showSettled))
                                    }
                                }
                            }
                            PointerEventType.Release -> {
                                catch = false
                            }
                            else -> {}
                        }
                    }
                }
            }

    ) {
        // topComponent.render()
    }
}