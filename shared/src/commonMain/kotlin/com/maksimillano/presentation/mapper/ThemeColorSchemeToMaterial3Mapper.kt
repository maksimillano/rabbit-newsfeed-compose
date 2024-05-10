package com.maksimillano.presentation.mapper

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

object ThemeColorSchemeToMaterial3Mapper {
    @Composable
    fun map(colorScheme: com.maksimillano.api.domain.theme.ColorScheme): ColorScheme {
        return ColorScheme(
            primary = colorScheme.primary,
            primaryContainer = colorScheme.primaryContainer,
            onPrimary = colorScheme.onPrimary,
            onPrimaryContainer = colorScheme.onPrimaryContainer,
            secondary = colorScheme.secondary,
            secondaryContainer =colorScheme.secondaryContainer,
            onSecondary = colorScheme.onSecondary,
            onSecondaryContainer = colorScheme.onSecondaryContainer,
            tertiary = colorScheme.tertiary,
            tertiaryContainer = colorScheme.tertiaryContainer,
            onTertiary = colorScheme.onTertiary,
            onTertiaryContainer = colorScheme.onTertiaryContainer,

            background = colorScheme.background,
            onBackground = colorScheme.onBackground,

            surface = colorScheme.surface,
            surfaceVariant = colorScheme.surfaceVariant,
            surfaceTint = colorScheme.surfaceTint,
            onSurface = colorScheme.onSurface,
            onSurfaceVariant = colorScheme.onSurfaceVariant,

            outline = colorScheme.outline,
            outlineVariant = colorScheme.outlineVariant,

            error = colorScheme.error,
            errorContainer = colorScheme.errorContainer,
            onError = colorScheme.onError,
            onErrorContainer = colorScheme.onErrorContainer,

            inverseSurface = colorScheme.inverseSurface,
            inversePrimary = colorScheme.inversePrimary,
            inverseOnSurface = colorScheme.inverseOnSurface,

            scrim = colorScheme.scrim
        )
    }
}