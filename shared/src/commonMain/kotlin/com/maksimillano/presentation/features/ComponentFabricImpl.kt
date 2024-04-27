package com.maksimillano.presentation.features

import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.base.ContextWrapper
import com.maksimillano.presentation.features.channels_list.ChannelsComponentImpl
import com.maksimillano.presentation.features.comment.CommentComponentImpl
import com.maksimillano.presentation.features.launch_drawer.LaunchComponentImpl
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponentImpl
import com.maksimillano.presentation.features.settings.SettingsComponentImpl

class ComponentFabricImpl : ComponentFabric {
    override fun createComponent(featureConfig: FeatureConfig, contextWrapper: ContextWrapper): RenderComponent {
        return when (featureConfig) {
            is FeatureConfig.Channels -> ChannelsComponentImpl(contextWrapper)
            is FeatureConfig.Launch -> LaunchComponentImpl(contextWrapper)
            is FeatureConfig.Newsfeed -> NewsFeedComponentImpl(contextWrapper)
            is FeatureConfig.Comments -> CommentComponentImpl(contextWrapper)
            is FeatureConfig.Settings -> SettingsComponentImpl(contextWrapper)
            else -> throw UnsupportedOperationException("Unsupported feature: $featureConfig")
        }
    }
}