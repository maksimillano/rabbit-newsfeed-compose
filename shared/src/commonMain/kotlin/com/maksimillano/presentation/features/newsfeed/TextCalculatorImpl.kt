package com.maksimillano.presentation.features.newsfeed

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Constraints
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.roundToInt

class TextCalculatorImpl(
    private val config: TextCalculatorConfig
) : TextCalculator {
    override fun calculate(text: String): List<TextCalculator.TextResult> {
        val measureResults = config.textMeasurer.measure(
            text = text,
            style = config.textStyleProvider(),
            constraints = Constraints.fixedWidth(config.widthProvider())
        )
        val entriesCount = ceil(measureResults.lineCount.toFloat() / config.maxLineEntry).roundToInt()

        val lineHeight = measureResults.size.height / measureResults.lineCount
        val entries =  (0 until entriesCount).map {
            val startLine = config.maxLineEntry * it
            val endLine = min(startLine + config.maxLineEntry - 1, measureResults.lineCount - 1)
            val linesCount = (endLine + 1) - startLine

            val startOffset = measureResults.getLineStart(startLine)
            val endOffset = measureResults.getLineEnd(endLine)
            TextCalculator.TextResult(
                text = text.substring(startOffset, endOffset),
                height = (lineHeight * linesCount).toInt()
            )
        }
        return entries
    }

    data class TextCalculatorConfig(
        val textMeasurer: TextMeasurer,
        val widthProvider: () -> Int,
        val textStyleProvider: () -> TextStyle,
        val maxLineEntry: Int
    )
}