package com.maksimillano.api.domain.features.account

import com.maksimillano.api.domain.loader.Supplier
import kotlinx.coroutines.flow.StateFlow

interface AccountSupplier : Supplier<AccountSupplierData> {
    override val state: StateFlow<AccountSupplierData>
}