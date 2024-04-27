package com.maksimillano.api.data.loader

import kotlinx.coroutines.flow.Flow

interface ListLoader<LoaderData> {
    val data: Flow<LoaderData>
    fun loadMore(direction: Direction)
    fun destroy()
}