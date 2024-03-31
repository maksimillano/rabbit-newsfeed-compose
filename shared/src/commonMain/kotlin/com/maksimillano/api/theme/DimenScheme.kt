package com.maksimillano.api.theme

import androidx.compose.ui.unit.TextUnit

interface DimenScheme {
    val postTitle: TextUnit
    val hint: TextUnit
    val postContent: TextUnit

    val textSmall: TextUnit
    val textMedium: TextUnit
    val textLarge: TextUnit
}