package com.maksimillano

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import korlibs.io.async.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

data class SampleNode(
    var number: Int
) : Modifier.Node(), ParentDataModifierNode {
    override fun onAttach() {
        coroutineScope.launch {

        }
        println()
    }
    override fun onDetach() {
        println()
    }

    override fun Density.modifyParentData(parentData: Any?): Any {
        return "Fucker"
    }
}
class SampleElement(val number: Int) : ModifierNodeElement<SampleNode>() {
    override fun create(): SampleNode = SampleNode(0)
    override fun equals(other: Any?): Boolean = number == other
    override fun hashCode(): Int = number.hashCode()
    override fun update(node: SampleNode) {
        node.number = number
    }
}

@Stable
fun Modifier.sample(number: Int): Modifier {
    return this then SampleElement(number)
}

@Stable
fun Modifier.inline(): Modifier {
    return layout { measurebles, constraints ->

        val placebles = measurebles.measure(constraints)
        layout(constraints.maxWidth, constraints.maxHeight) {
            placebles.place(100, 100)
        }
    }
}

@Composable
fun ColumnLegend(
    modifier: Modifier = Modifier,
    content: @Composable @UiComposable () -> Unit
) {
    Layout(
        content,
        modifier,
    ) { measureables, contraints ->
        val measureble = measureables.first()
        val parentData = measureble.parentData
        val width = measureble.minIntrinsicWidth(200)
        val childConstraint = Constraints.fixed(width, 200)
        val placeble = measureble.measure(childConstraint)
        val realWidth = placeble.width
        val realHeight = placeble.height
        layout(contraints.maxWidth, contraints.maxHeight) {
            placeble.place(0, 0)
        }
    }
}

var launched = false
val testScope = CoroutineScope(Dispatchers.Default)
fun launch() {
    testScope.launch {
        var counter = 0
        while (true) {
            delay(5000)
            flow.emit("${++counter}")
        }
    }
}
val flow = MutableStateFlow("start")
val flowObserver = flow

@Composable
fun Header(modifier: Modifier = Modifier) {
    if (!launched) {
        launched = true
        launch()
    }
    var state by remember {
        mutableStateOf(true)
    }
    if (state) {
        LaunchedEffect(key1 = "") {
            launch {
                try {
                    flowObserver.collect {
                        println(it)
                        if (it == "3") {
                            state = false
                        }
                    }
                } catch (throwable: Throwable) {
                    println(throwable.message)
                }
            }

        }
    }
    ColumnLegend(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFB0BCFF))
    ) {
        Text(
            text = "H e l l o s a i m o n",
            fontSize = 21.sp,
//            style = TextStyle(
//                lineBreak = LineBreak.Paragraph
//            ),
            modifier = Modifier
                .background(Color.Magenta)
                .sample(1)
        )
    }
}


@Composable
@Preview()
fun HeaderPreview() {
    Header(
        modifier = Modifier.background(Color(0xFFE4E4E4))
    )
}