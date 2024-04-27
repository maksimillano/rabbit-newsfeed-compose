package com.maksimillano.api.data.loader

import kotlinx.coroutines.flow.Flow

interface Loader<LoaderData> {
    val data: Flow<LoaderData>
    fun load()
    fun destroy()
}