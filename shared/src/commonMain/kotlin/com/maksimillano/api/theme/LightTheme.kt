package com.maksimillano.api.theme

interface LightTheme : Theme {
    override val isLight: Boolean
        get() = true
}