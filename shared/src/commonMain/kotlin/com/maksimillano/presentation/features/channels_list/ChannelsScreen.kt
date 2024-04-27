package com.maksimillano.presentation.features.channels_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ChannelsScreen(component: ChannelsComponent) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xfff2d659))
            .clickable {
                component.navigator.onButtonBack()
            }
    ) {
        Text("${ChannelsComponentImpl::class.simpleName}", modifier = Modifier.align(Alignment.Center))
    }
}