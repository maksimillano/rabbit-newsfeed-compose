package com.maksimillano.presentation.features.root

import com.arkivanov.decompose.ComponentContext
import com.maksimillano.presentation.features.ComponentFabric
import com.maksimillano.presentation.features.FeatureConfig

class RootComponentConfigImpl(
    override val context: ComponentContext,
    override val initialFeatureConfig: FeatureConfig,
    override val componentFabric: ComponentFabric,
) : RootComponentConfig