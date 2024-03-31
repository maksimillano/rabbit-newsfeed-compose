package com.maksimillano.presentation.base.stack.animator.shift

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.stack.AnimatorEventProducer
import com.maksimillano.util.pxToDpValue

@Composable
internal fun <ENTRY : Any> ShiftStackAnimator<ENTRY>.showDragging(
    topComponent: BaseComponent,
    bottomComponent: BaseComponent,
    animatorEventProducer: AnimatorEventProducer<ENTRY>, // OutEnd event
    shiftStateCallback: (ShiftingOutState) -> Unit // Finger up -> animate reset or animate pop
) {
    val density = LocalDensity.current
    var offsetProvider by remember { mutableStateOf(0.dp) }

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Main)
                        val notConsumed = event.changes.all { !it.isConsumed }
//                        if (!notConsumed) {
//                            continue
//                        }

                        when (event.type) {
                            PointerEventType.Move -> {
                                event.changes.forEach { change ->
                                    change.consume()
                                    offsetProvider = change.position.x.pxToDpValue(density)
                                }
                            }
                            PointerEventType.Release -> {
                                shiftStateCallback(ShiftingOutState.TouchUp(offsetProvider))
                            }
                        }
                    }
                }
            }
    ) {

        // bottomComponent.render()
        Box(
            Modifier
                .fillMaxSize()
                .offset(x = offsetProvider)
        ) {
            // topComponent.render()
        }
    }
}