package com.maksimillano.api.theme

interface CustomComponents {
    val appBar: AppBarComponent

    enum class AppBarComponent(val key: String) {
        Default("default"),
        DayNight("day_night"),
        HelloKitty("hello_kitty")
    }
}