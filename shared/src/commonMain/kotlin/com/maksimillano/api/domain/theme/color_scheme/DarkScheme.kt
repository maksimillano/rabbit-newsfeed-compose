package com.maksimillano.api.domain.theme.color_scheme

import androidx.compose.ui.graphics.Color
import com.maksimillano.api.domain.theme.ColorScheme

object DarkScheme : ColorScheme {
    override val primary = Color(0xff222222)
    override val onPrimary = Color(0xffe1e3e6)
    override val primaryContainer = Color(0xff626d7a)
    override val onPrimaryContainer = Color(0xff626d7a)
    override val inversePrimary = Color(0xff626d7a)
    override val secondary = Color(0xff2e2e2e)
    override val onSecondary = Color(0xffe7e7e7)
    override val secondaryContainer = Color(0xff626d7a)
    override val onSecondaryContainer = Color(0xff626d7a)
    override val tertiary = Color(0xff7474f1)
    override val onTertiary = Color(0xff222222)
    override val tertiaryContainer = Color(0xff000000)
    override val onTertiaryContainer = Color(0xff2b5885)
    override val background = Color(0xff141414)
    override val onBackground = Color(0xff99a2ad)
    override val surface = Color(0xff222222)
    override val onSurface = Color(0xffedeef0)
    override val surfaceVariant = Color(0xffffffff)
    override val onSurfaceVariant = Color(0xffffffff)
    override val surfaceTint = Color(0xfff0f2f5)
    override val inverseSurface = Color(0xff626d7a)
    override val inverseOnSurface = Color(0xfff9f9f9)
    override val error = Color(0xff447bba)
    override val onError = Color(0xfff5f5f5)
    override val errorContainer: Color = Color(0xffdce1e5)
    override val onErrorContainer = Color(0xffc70c23)
    override val outline = Color(0xff474747)
    override val outlineVariant = Color(0xff1f1f1f)
    override val scrim = Color(0xffc70c23)
}