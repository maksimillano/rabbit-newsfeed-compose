package com.maksimillano

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import korlibs.io.async.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TestPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .pointerInput(Unit) {
//                Logger.d("Lalala") { "Go pointerInput blocks" }
//                while (true) {
//                    awaitPointerEventScope {
////                        Logger.d("Lalala") { "Go awaitPointerEventScope" }
//                        val pointer = awaitPointerEvent(PointerEventPass.Initial)
//                        val pointerMain = awaitPointerEvent(PointerEventPass.Main)
//
//                        Logger.d("Lalala") {
//                            "Pointer Init ${pointer.type} ${pointer.changes.joinToString { it.position.toString() + " * consumed=" + it.isConsumed }}"
//                        }
//                        Logger.d("Lalala") {
//                            "Pointer Main ${pointerMain.type} ${pointerMain.changes.joinToString { it.position.toString() + " * consumed=" + it.isConsumed }}"
//                        }
//                    }
//                }
//            }
            .background(Color(0xFF03A9F4)),
        contentAlignment = Alignment.CenterStart
    ) {
        Circle(
            modifier = Modifier
                .fillMaxSize()
                .swipeToDismiss {}
//                .pointerInput(Unit) {
//                    Logger.d("Lalala") { "Go pointerInput circle" }
//                    while (true) {
//                        awaitPointerEventScope {
////                            Logger.d("Lalala") { "Go awaitPointerEventScope" }
//                            val pointer = awaitPointerEvent(PointerEventPass.Initial)
//                            pointer.changes.forEach { it.consume() }
//                            Logger.d("Lalala") {
//                                "Circle Pointer ${pointer.type} ${pointer.changes.joinToString { it.position.toString() + " * consumed=" + it.isConsumed }}"
//                            }
//
//                        }
//                    }
//                }
        )
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .drawBehind {
//                drawRect(
//                    color = Color(0x0FFFFFFF),
//                    size = drawContext.size
//                )
//            },
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Circle(
//            modifier = Modifier
//                .swipeToDismiss {
//
//                }
//        )
//    }
}

fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val decay = splineBasedDecay<Float>(density)

    Logger.d("Lalala") { "Update... ${offsetX.value}" }
    pointerInput(Unit) {
        // Used to calculate fling decay.
        // Use suspend functions for touch events and the Animatable.
        while (true) {
            val velocityTracker = VelocityTracker()
            // Stop any ongoing animation.
            offsetX.stop()
            Logger.d("Lalala") { "Go go" }
            awaitPointerEventScope {
                // Detect a touch down event.
                val awaitFirstDown = awaitFirstDown()
                val pointerId = awaitFirstDown.id

                Logger.d("Lalala") { "Touch id $pointerId" }
                horizontalDrag(pointerId) { change ->
                    // Update the animation value with touch events.
                    scope.launch {
                        offsetX.snapTo(
                            offsetX.value + change.positionChange().x
                        )
                    }

                    Logger.d("Lalala") { "Dragging ${change.position.x}" }
                    velocityTracker.addPosition(
                        change.uptimeMillis,
                        change.position
                    )
                }
            }

            Logger.d("Lalala") { "Outline" }
            // No longer receiving touch events. Prepare the animation.
            val velocity = velocityTracker.calculateVelocity().x
            val targetOffsetX = decay.calculateTargetValue(
                offsetX.value,
                velocity
            )
            // The animation stops when it reaches the bounds.
            offsetX.updateBounds(
                lowerBound = -size.width.toFloat(),
                upperBound = size.width.toFloat()
            )
            Logger.d("Lalala") { "Animating: ${targetOffsetX.absoluteValue} And ${size.width}" }
            if (targetOffsetX.absoluteValue <= size.width) {
                // Not enough velocity; Slide back.
                Logger.d("Lalala") { "Por que no" }
                offsetX.animateTo(
                    targetValue = 0f,
                    initialVelocity = velocity
                )
            } else {
                // The element was swiped away.
                offsetX.animateDecay(velocity, decay)
                onDismissed()
            }
        }
    }
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}

@Composable
private fun Circle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
//            .size(100.dp)
            .background(Color.Red)
    ) {
    }
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())