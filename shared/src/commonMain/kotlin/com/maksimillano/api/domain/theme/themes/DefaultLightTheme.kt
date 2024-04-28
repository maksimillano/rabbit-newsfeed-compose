package com.maksimillano.api.domain.theme.themes

import com.maksimillano.api.domain.theme.BackgroundStyle
import com.maksimillano.api.domain.theme.ColorScheme
import com.maksimillano.api.domain.theme.CustomComponents
import com.maksimillano.api.domain.theme.DefaultCustomComponents
import com.maksimillano.api.domain.theme.DefaultThemeIds
import com.maksimillano.api.domain.theme.DimenScheme
import com.maksimillano.api.domain.theme.LightTheme
import com.maksimillano.api.domain.theme.color_scheme.LightScheme
import com.maksimillano.api.domain.theme.dimen.DefaultDimenScheme

object DefaultLightTheme : LightTheme {
    override val colorScheme: ColorScheme = LightScheme
    override val dimenScheme: DimenScheme = DefaultDimenScheme()
    override val backgroundStyle: BackgroundStyle = BackgroundStyle.Default
    override val customComponents: CustomComponents = DefaultCustomComponents()
    override val themeId: Int = DefaultThemeIds.DEFAULT_LIGHT_THEME_ID
}