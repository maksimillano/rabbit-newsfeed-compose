package com.maksimillano.api.domain.theme

interface DarkTheme : Theme {
    override val isLight: Boolean
        get() = false
}