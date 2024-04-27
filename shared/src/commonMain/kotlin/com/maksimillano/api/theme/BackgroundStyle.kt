package com.maksimillano.api.theme

sealed interface BackgroundStyle {
    data object Default : BackgroundStyle
    data class CustomBackground(val resource: String) : BackgroundStyle
}