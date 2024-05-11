package com.maksimillano.presentation.features.newsfeed

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle

interface TextCalculator {
    fun calculate(text: String): List<String>
}