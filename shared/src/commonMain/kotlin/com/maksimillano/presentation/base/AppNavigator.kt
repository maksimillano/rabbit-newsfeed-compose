package com.maksimillano.presentation.base

import com.maksimillano.presentation.features.FeatureConfig

interface AppNavigator {
    fun onNavigate(featureConfig: FeatureConfig)
    fun onButtonBack()
}