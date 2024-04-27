package com.maksimillano.api.model

sealed interface Platform {
    data object Web : Platform
    data object Desktop : Platform
    data object Android : Platform
    data object Ios : Platform
}