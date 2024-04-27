package com.maksimillano

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.maksimillano.presentation.features.ComponentFabricImpl
import com.maksimillano.presentation.features.FeatureConfig
import com.maksimillano.presentation.features.root.RootComponentConfigImpl
import com.maksimillano.presentation.features.root.impl.SmallRootComponent

@Composable
fun MainView(componentContext: ComponentContext) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top + WindowInsetsSides.Horizontal)),
            ) {
                val rootComponentConfig = RootComponentConfigImpl(
                    componentContext,
                    FeatureConfig.Launch,
                    ComponentFabricImpl()
                )
                val mainComponent = SmallRootComponent(rootComponentConfig)
                mainComponent.render()
            }
        }
    }
}