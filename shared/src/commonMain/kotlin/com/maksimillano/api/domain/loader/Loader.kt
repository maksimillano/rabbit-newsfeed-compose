package com.maksimillano.api.domain.loader

import kotlinx.coroutines.flow.Flow

interface Loader<D : LoaderData> {
    val state: Flow<State<D>>
    fun loadMore(loadMode: LoadMode)
    fun destroy()
    fun reset()
    fun resetAndLoad()
    data class State<D>(val data: D, val hasMore: Boolean)
}