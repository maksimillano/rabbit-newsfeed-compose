package com.maksimillano.presentation.features.root

import com.arkivanov.decompose.ComponentContext
import com.maksimillano.presentation.features.ComponentFabric
import com.maksimillano.presentation.features.FeatureConfig

interface RootComponentConfig {
    val context: ComponentContext
    val initialFeatureConfig: FeatureConfig
    val componentFabric: ComponentFabric
}