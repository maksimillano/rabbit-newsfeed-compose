package com.maksimillano.api.domain.theme

sealed interface BackgroundStyle {
    data object Default : BackgroundStyle
    data class CustomBackground(val resource: String) : BackgroundStyle
}