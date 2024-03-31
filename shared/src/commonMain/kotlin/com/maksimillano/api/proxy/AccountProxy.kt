package com.maksimillano.api.proxy

import com.maksimillano.api.model.newsfeed.newfeed.PostFeed
import com.maksimillano.api.model.user.EngineUser
import com.maksimillano.api.preference.GlobalPreferences
import com.maksimillano.api.preference.UserPreferences
import com.maksimillano.api.theme.Theme
import com.maksimillano.data.model.newsfeed.newsfeed.PostFeedImpl
import kotlinx.coroutines.flow.StateFlow

interface AccountProxy {
    val userPreferences: UserPreferences
    val globalPreferences: GlobalPreferences
    val theme: StateFlow<Theme>
    val engineUser: StateFlow<EngineUser>
}