package com.maksimillano.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.maksimillano.AppConstants
import com.maksimillano.api.theme.DimenScheme

@Composable
inline fun dimenThemed(
    attribute: (DimenScheme) -> TextUnit
): TextUnit {
    TODO()
//    val engine = EngineProvider.get()
//    val theme by engine.theme.collectAsState()
//    val currentSp by animateFloatAsState(
//        targetValue = attribute(theme.dimenScheme).value,
//        animationSpec = tween(
//            AppConstants.THEME_ANIMATION_DURATION
//        )
//
//    )
//    return currentSp.sp
}