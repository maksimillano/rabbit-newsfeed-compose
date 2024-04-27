package com.maksimillano.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

inline fun Modifier.clickableNoRipple(crossinline onCLick: () -> Unit): Modifier {
    return clickable(MutableInteractionSource(), null) {
        onCLick()
    }
}