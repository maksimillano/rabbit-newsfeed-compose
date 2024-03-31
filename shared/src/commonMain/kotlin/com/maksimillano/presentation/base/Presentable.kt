package com.maksimillano.presentation.base

import androidx.compose.runtime.Composable

interface Presentable {
    @Composable
    fun render()
}