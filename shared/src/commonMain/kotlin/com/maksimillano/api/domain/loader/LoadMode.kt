package com.maksimillano.api.domain.loader

sealed interface LoadMode {
    data object Latest : LoadMode
    data class Before(val count: Int) : LoadMode
    data class Since(val count: Int) : LoadMode
}