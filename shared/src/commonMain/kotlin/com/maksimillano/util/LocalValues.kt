package com.maksimillano.util

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle

var localPostWidthProvider: () -> Int = { 0 }
var localPostTextStyleProvider: () -> TextStyle = { TextStyle() }
lateinit var localTextMeasurerProvider: TextMeasurer
