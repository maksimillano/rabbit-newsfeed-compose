package com.maksimillano

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maksimillano.presentation.features.newsfeed.TextCalculator
import com.maksimillano.presentation.features.newsfeed.TextCalculatorImpl
import com.maksimillano.util.dpToPx
import com.maksimillano.util.pxToDp

@Composable
@Preview
fun TextCalculatorTest() {
    val width = 100.dp
    val textMeasurer = rememberTextMeasurer()
    val dpToPx = width.dpToPx()
    val lineHeight = 14.sp
    val style = TextStyle(
        fontSize = lineHeight,
        color = Color(0xFF1F2966),
        background = Color(0xFFC7CFFF),
        textIndent = TextIndent(),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
//        lineHeightStyle = LineHeightStyle(
//            trim = LineHeightStyle.Trim.FirstLineTop,
//            alignment = LineHeightStyle.Alignment.Center
//        )
    )
    val text =
        "The scroll amount can be obtained, no calculation required, in the items themselves, by attaching an onGloballyPosition{ it.positionInParent()} modifier to one or more items. Then, the items can do what they need to do with their own scroll position, such as offsetting some screen-drawing y coordinate"
    // val density = LocalDensity.current.density
    // val scale = LocalDensity.current.fontScale
    val textCalculator: TextCalculator = remember {
        val config = TextCalculatorImpl.TextCalculatorConfig(
            textMeasurer = textMeasurer,
            widthProvider = { dpToPx },
            textStyleProvider = { style },
            maxLineEntry = 4,
            lineTextProvider = { 54f }
        )
        TextCalculatorImpl(config)
    }

    val strings = remember {
        textCalculator.calculate(text)
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = text, style = style, modifier = Modifier
            .width(width)
            .background(Color.Red)
            .onSizeChanged {
                val height = it.height
                val lineH = height / 22
                println(lineH)
            }
        )

        Column(
            modifier = Modifier
                .onSizeChanged {
                    val height = it.height
                    println()
                }
        ) {
            strings.forEach { textResult ->
                Text(text = textResult.text, style = style,
                    modifier = Modifier
                        .width(width)
                        .height(textResult.height.pxToDp())
                        .background(Color(0xFFE91E63)))
            }
        }
    }

}

// sp(TextUnit) → px(Float)
@Composable
internal fun TextUnit.spToPx(): Float {
    return this.value * LocalDensity.current.fontScale
}