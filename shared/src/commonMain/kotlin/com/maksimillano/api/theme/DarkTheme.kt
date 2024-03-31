package com.maksimillano.api.theme

interface DarkTheme : Theme {
    override val isLight: Boolean
        get() = false
}