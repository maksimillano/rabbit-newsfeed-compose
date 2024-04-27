package com.maksimillano.api.theme.themes

import com.maksimillano.api.theme.BackgroundStyle
import com.maksimillano.api.theme.ColorScheme
import com.maksimillano.api.theme.CustomComponents
import com.maksimillano.api.theme.DarkTheme
import com.maksimillano.api.theme.DefaultCustomComponents
import com.maksimillano.api.theme.DefaultThemeIds
import com.maksimillano.api.theme.DimenScheme
import com.maksimillano.api.theme.color_scheme.DarkScheme
import com.maksimillano.api.theme.dimen.DefaultDimenScheme

object DefaultDarkTheme : DarkTheme {
    override val colorScheme: ColorScheme = DarkScheme
    override val dimenScheme: DimenScheme = DefaultDimenScheme()
    override val backgroundStyle: BackgroundStyle = BackgroundStyle.Default
    override val customComponents: CustomComponents = DefaultCustomComponents()
    override val themeId: Int = DefaultThemeIds.DEFAULT_DARK_THEME_ID
}