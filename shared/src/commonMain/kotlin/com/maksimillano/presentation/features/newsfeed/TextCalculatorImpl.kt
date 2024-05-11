package com.maksimillano.presentation.features.newsfeed

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import kotlin.math.roundToInt

class TextCalculatorImpl(
    private val config: TextCalculatorConfig
) : TextCalculator {
    override fun calculate(text: String): List<String> {
        val measureResults = config.textMeasurer.measure(
            text = text,
            style = config.textStyleProvider(),
            constraints = Constraints.fixedWidth(config.widthProvider())
        )

        val entries = (measureResults.lineCount.toFloat() / config.maxLineEntry).roundToInt()

        return emptyList()
    }

    data class TextCalculatorConfig(
        val textMeasurer: TextMeasurer,
        val widthProvider: () -> Int,
        val textStyleProvider: () -> TextStyle,
        val maxLineEntry: Int
    )
}