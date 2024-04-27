package com.maksimillano.presentation.features

import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.base.ContextWrapper

interface ComponentFabric {
    fun createComponent(featureConfig: FeatureConfig, contextWrapper: ContextWrapper): RenderComponent
}