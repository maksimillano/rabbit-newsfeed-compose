package com.maksimillano.presentation.base.stack

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class StackConfig(
    val color: Long,
    val number: Int,
) : Parcelable