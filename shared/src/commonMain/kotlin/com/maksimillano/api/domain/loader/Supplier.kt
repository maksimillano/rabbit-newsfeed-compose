package com.maksimillano.api.domain.loader

import kotlinx.coroutines.flow.Flow

interface Supplier<D> {
    val state: Flow<D>
}