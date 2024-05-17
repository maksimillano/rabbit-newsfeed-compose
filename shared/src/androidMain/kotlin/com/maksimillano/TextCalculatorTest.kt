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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
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
    val style = TextStyle(
        fontSize = 14.sp,
        color = Color(0xFF2B2B2B),
        background = Color(0xFFC7CFFF),
        textIndent = TextIndent(),
        lineHeightStyle = LineHeightStyle(
            trim = LineHeightStyle.Trim.None,
            alignment = LineHeightStyle.Alignment.Top
        )
    )
    val text = "The scroll amount can be obtained, no calculation required, in the items themselves, by attaching an onGloballyPosition{ it.positionInParent()} modifier to one or more items. Then, the items can do what they need to do with their own scroll position, such as offsetting some screen-drawing y coordinate"
    val textCalculator: TextCalculator = remember {
        val config = TextCalculatorImpl.TextCalculatorConfig(
            textMeasurer = textMeasurer,
            widthProvider = { dpToPx },
            textStyleProvider = { style },
            maxLineEntry = 4
        )
        TextCalculatorImpl(config)
    }

    val strings = remember {
        textCalculator.calculate(text)
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = text, style = style, modifier = Modifier
            .width(width)
            .background(Color.Red))

        Column {
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