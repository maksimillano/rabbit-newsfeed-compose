package com.maksimillano.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.maksimillano.util.BuildConstants
import com.maksimillano.presentation.theme.AppThemeProvider
import com.maksimillano.api.domain.theme.ColorScheme

@Composable
inline fun colorThemed(
    action: (ColorScheme) -> Color
): Color {
    val theme by AppThemeProvider.theme.collectAsState()
    val animatedValue by animateColorAsState(
        targetValue = action(theme.colorScheme),
        animationSpec = tween(BuildConstants.THEME_ANIMATION_DURATION)
    )
    return animatedValue
}
