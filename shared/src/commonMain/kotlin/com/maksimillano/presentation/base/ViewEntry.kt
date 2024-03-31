package com.maksimillano.presentation.base

import androidx.compose.runtime.Composable

data class ViewEntry(val action: @Composable () -> Unit) {
    @Composable
    operator fun invoke() = action()
}