package com.maksimillano.presentation.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.maksimillano.presentation.component.backgroundThemed
import com.maksimillano.presentation.features.main.MainComponent

@Composable
fun MainScreen(
    mainComponent: MainComponent
) {
    val state = mainComponent.state.subscribeAsState()

    Box(
        modifier = Modifier.fillMaxSize()
            .backgroundThemed { it.backgroundColor }
    ) {
        val stackChild = state.value.items.last()
        stackChild.instance()
    }
}