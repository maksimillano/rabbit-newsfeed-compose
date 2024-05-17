package com.maksimillano.api.domain.theme.color_scheme

import androidx.compose.ui.graphics.Color
import com.maksimillano.api.domain.theme.ColorScheme

object LightScheme : ColorScheme {
    override val primary = Color(0xffffffff)
    override val onPrimary = Color(0xff000000)
    override val primaryContainer = Color(0xff626d7a)
    override val onPrimaryContainer = Color(0xff626d7a)
    override val inversePrimary = Color(0xff626d7a)
    override val secondary = Color(0xfff0f0f0)
    override val onSecondary = Color(0xff222222)
    override val secondaryContainer = Color(0xfff2f2f2)
    override val onSecondaryContainer = Color(0xff323232)
    override val tertiary = Color(0xff447bba)
    override val onTertiary = Color(0xffffffff)
    override val tertiaryContainer = Color(0xff000000)
    override val onTertiaryContainer = Color(0xff2b5885)
    override val background = Color(0xffe7e7e7)
    override val onBackground = Color(0xff8c8c8c)
    override val surface = Color(0xffffffff)
    override val onSurface = Color(0xff0b0b0b)
    override val surfaceVariant = Color(0xffffffff)
    override val onSurfaceVariant = Color(0xffffffff)
    override val surfaceTint = Color(0xfff0f2f5)
    override val inverseSurface = Color(0xff626d7a)
    override val inverseOnSurface = Color(0xfff9f9f9)
    override val error = Color(0xff447bba)
    override val onError = Color(0xfff5f5f5)
    override val errorContainer: Color = Color(0xffdce1e5)
    override val onErrorContainer = Color(0xffc70c23)
    override val outline = Color(0xff6c6c6c)
    override val outlineVariant = Color(0xffd1d1d1)
    override val scrim = Color(0xffc70c23)
}