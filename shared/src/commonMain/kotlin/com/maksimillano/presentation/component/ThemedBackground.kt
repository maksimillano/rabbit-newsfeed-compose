package com.maksimillano.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.maksimillano.AppConstants
import com.maksimillano.presentation.theme.AppThemeProvider
import com.maksimillano.api.theme.ColorScheme

@Composable
fun Modifier.backgroundThemed(shape: Shape = RectangleShape, attribute: (ColorScheme) -> Color): Modifier {
    val currentTheme by AppThemeProvider.theme.collectAsState()
    val backgroundColor by animateColorAsState(
        attribute(currentTheme.colorScheme),
        animationSpec = tween(
            durationMillis = AppConstants.THEME_ANIMATION_DURATION
        )
    )
    return this then Modifier.background(backgroundColor, shape)
}