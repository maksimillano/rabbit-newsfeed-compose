package com.maksimillano.api.domain.theme.themes

import com.maksimillano.api.domain.theme.BackgroundStyle
import com.maksimillano.api.domain.theme.ColorScheme
import com.maksimillano.api.domain.theme.CustomComponents
import com.maksimillano.api.domain.theme.DarkTheme
import com.maksimillano.api.domain.theme.DefaultCustomComponents
import com.maksimillano.api.domain.theme.DefaultThemeIds
import com.maksimillano.api.domain.theme.DimenScheme
import com.maksimillano.api.domain.theme.color_scheme.DarkScheme
import com.maksimillano.api.domain.theme.dimen.DefaultDimenScheme

object DefaultDarkTheme : DarkTheme {
    override val colorScheme: ColorScheme = DarkScheme
    override val dimenScheme: DimenScheme = DefaultDimenScheme()
    override val backgroundStyle: BackgroundStyle = BackgroundStyle.Default
    override val customComponents: CustomComponents = DefaultCustomComponents()
    override val themeId: Int = DefaultThemeIds.DEFAULT_DARK_THEME_ID
}