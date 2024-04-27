package com.maksimillano.api.data.account

import com.maksimillano.api.data.loader.Loader
import kotlinx.coroutines.flow.StateFlow

interface AccountLoader : Loader<AccountLoaderData> {
    override val data: StateFlow<AccountLoaderData>
}