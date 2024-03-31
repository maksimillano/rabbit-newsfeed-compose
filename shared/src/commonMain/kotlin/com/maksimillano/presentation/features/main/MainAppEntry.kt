package com.maksimillano.presentation.features.main

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class MainAppEntry : Parcelable {
    data object Launch : MainAppEntry()
    data object Comment : MainAppEntry()
}