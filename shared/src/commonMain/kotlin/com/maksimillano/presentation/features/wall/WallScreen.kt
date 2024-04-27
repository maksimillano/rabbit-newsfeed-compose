package com.maksimillano.presentation.features.wall

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.maksimillano.presentation.features.FeatureConfig

@Composable
fun WallScreen(component: WallComponent) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xffdf744d))
            .clickable { component.navigator.onButtonBack() },

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${WallComponentImpl::class.simpleName}")

        Button(onClick = {component.navigator.onNavigate(FeatureConfig.Profile(1))}) { Text(text = "Profile") }
        Button(onClick = {component.navigator.onNavigate(FeatureConfig.Comments)}) { Text(text = "Comments") }
    }
}