package com.maksimillano.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.maksimillano.api.theme.ColorScheme

@Composable
inline fun colorThemed(
    action: (ColorScheme) -> Color
): Color {
    TODO()
//    val engine = EngineProvider.get()
//    val theme by engine.theme.collectAsState()
//    val animatedValue by animateColorAsState(
//        targetValue = action(theme.colorScheme),
//        animationSpec = tween(AppConstants.THEME_ANIMATION_DURATION)
//    )
//    return animatedValue
}
