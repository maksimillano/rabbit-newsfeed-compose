package com.maksimillano.data.proxy

import com.maksimillano.api.model.user.EngineUser
import com.maksimillano.api.preference.GlobalPreferences
import com.maksimillano.api.preference.UserPreferences
import com.maksimillano.api.proxy.AccountProxy
import com.maksimillano.api.theme.Theme
import kotlinx.coroutines.flow.StateFlow

class AccountProxyImpl : AccountProxy {
    override val userPreferences: UserPreferences
        get() = TODO("Not yet implemented")
    override val globalPreferences: GlobalPreferences
        get() = TODO("Not yet implemented")
    override val theme: StateFlow<Theme>
        get() = TODO("Not yet implemented")
    override val engineUser: StateFlow<EngineUser>
        get() = TODO("Not yet implemented")
}