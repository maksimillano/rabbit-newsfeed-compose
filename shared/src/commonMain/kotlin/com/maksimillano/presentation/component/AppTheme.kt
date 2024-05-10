package com.maksimillano.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import com.maksimillano.api.domain.theme.color_scheme.DarkScheme
import com.maksimillano.api.domain.theme.color_scheme.LightScheme
import com.maksimillano.presentation.mapper.ThemeColorSchemeToMaterial3Mapper
import com.maksimillano.presentation.theme.AppThemeProvider
import com.maksimillano.util.BuildConstants

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    if (LocalInspectionMode.current) {
        val scheme = if (isSystemInDarkTheme()) {
            DarkScheme
        } else {
            LightScheme
        }
        val colorScheme = ThemeColorSchemeToMaterial3Mapper.map(scheme)

        MaterialTheme(
            colorScheme = colorScheme.withSwitchAnimation(),
            content = content
        )
    } else {
        val theme by AppThemeProvider.theme.collectAsState()
        val colorScheme = ThemeColorSchemeToMaterial3Mapper.map(theme.colorScheme)

        MaterialTheme(
            colorScheme = colorScheme.withSwitchAnimation(),
            content = content
        )
    }
}

@Composable
fun ColorScheme.withSwitchAnimation() = copy(
    primary = animateColor(primary),
    primaryContainer = animateColor(primaryContainer),
    onPrimary = animateColor(onPrimary),
    onPrimaryContainer = animateColor(onPrimaryContainer),
    secondary = animateColor(secondary),
    secondaryContainer = animateColor(secondaryContainer),
    onSecondary = animateColor(onSecondary),
    onSecondaryContainer = animateColor(onSecondaryContainer),
    tertiary = animateColor(tertiary),
    tertiaryContainer = animateColor(tertiaryContainer),
    onTertiary = animateColor(onTertiary),
    onTertiaryContainer = animateColor(onTertiaryContainer),

    background = animateColor(background),
    onBackground = animateColor(onBackground),

    surface = animateColor(surface),
    surfaceVariant = animateColor(surfaceVariant),
    surfaceTint = animateColor(surfaceTint),
    onSurface = animateColor(onSurface),
    onSurfaceVariant = animateColor(onSurfaceVariant),

    outline = animateColor(outline),
    outlineVariant = animateColor(outlineVariant),

    error = animateColor(error),
    errorContainer = animateColor(errorContainer),
    onError = animateColor(onError),
    onErrorContainer = animateColor(onErrorContainer),

    inverseSurface = animateColor(inverseSurface),
    inversePrimary = animateColor(inversePrimary),
    inverseOnSurface = animateColor(inverseOnSurface),

    scrim = animateColor(scrim)
)

@Composable
fun animateColor(target: Color): Color {
    return animateColorAsState(
        targetValue = target,
        animationSpec = tween(BuildConstants.THEME_ANIMATION_DURATION),
        label = ""
    ).value
}





//@Immutable
//data class ExtendedColors(
//    val tertiary: Color,
//    val onTertiary: Color
//)
//
//val LocalExtendedColors = staticCompositionLocalOf {
//    ExtendedColors(
//        tertiary = Color.Unspecified,
//        onTertiary = Color.Unspecified
//    )
//}
//
//@Composable
//fun ExtendedTheme(
//    /* ... */
//    content: @Composable () -> Unit
//) {
//    val extendedColors = ExtendedColors(
//        tertiary = Color(0xFFA8EFF0),
//        onTertiary = Color(0xFF002021)
//    )
//    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
//        MaterialTheme(
//            /* colors = ..., typography = ..., shapes = ... */
//            content = content
//        )
//    }
//}
//
//// Use with eg. ExtendedTheme.colors.tertiary
//object ExtendedTheme {
//    val colors: ExtendedColors
//        @Composable
//        get() = LocalExtendedColors.current
//}