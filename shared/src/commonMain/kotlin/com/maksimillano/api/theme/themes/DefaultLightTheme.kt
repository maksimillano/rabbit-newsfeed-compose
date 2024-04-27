package com.maksimillano.api.theme.themes

import com.maksimillano.api.theme.BackgroundStyle
import com.maksimillano.api.theme.ColorScheme
import com.maksimillano.api.theme.CustomComponents
import com.maksimillano.api.theme.DefaultCustomComponents
import com.maksimillano.api.theme.DefaultThemeIds
import com.maksimillano.api.theme.DimenScheme
import com.maksimillano.api.theme.LightTheme
import com.maksimillano.api.theme.color_scheme.LightScheme
import com.maksimillano.api.theme.dimen.DefaultDimenScheme

object DefaultLightTheme : LightTheme {
    override val colorScheme: ColorScheme = LightScheme
    override val dimenScheme: DimenScheme = DefaultDimenScheme()
    override val backgroundStyle: BackgroundStyle = BackgroundStyle.Default
    override val customComponents: CustomComponents = DefaultCustomComponents()
    override val themeId: Int = DefaultThemeIds.DEFAULT_LIGHT_THEME_ID
}