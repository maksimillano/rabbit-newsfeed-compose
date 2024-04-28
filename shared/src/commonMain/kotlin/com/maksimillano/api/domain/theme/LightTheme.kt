package com.maksimillano.api.domain.theme

interface LightTheme : Theme {
    override val isLight: Boolean
        get() = true
}