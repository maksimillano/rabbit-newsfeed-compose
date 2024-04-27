package com.maksimillano.presentation.features

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed interface FeatureConfig : Parcelable {
    @Parcelize
    data object Launch : FeatureConfig
    @Parcelize
    data object Navigation : FeatureConfig
    @Parcelize
    data object Newsfeed : FeatureConfig
    @Parcelize
    data object Channels : FeatureConfig
    @Parcelize
    data object Popular : FeatureConfig
    @Parcelize
    data object Comments : FeatureConfig
    @Parcelize
    data class Wall(val channelId: Long) : FeatureConfig
    @Parcelize
    data class Profile(val channelId: Long) : FeatureConfig
    @Parcelize
    data object Favorites : FeatureConfig
    @Parcelize
    data object Settings : FeatureConfig
}