package com.maksimillano.api.theme

sealed interface Theme {
    val colorScheme: ColorScheme
    val dimenScheme: DimenScheme
    val backgroundStyle: BackgroundStyle
    val customComponents: CustomComponents

    val isLight: Boolean
    val themeName: String
        get() = this::class.simpleName!!
    val themeId: Int
        get() = 0
}
