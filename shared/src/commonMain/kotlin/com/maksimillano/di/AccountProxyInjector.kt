package com.maksimillano.di

import com.maksimillano.api.proxy.AccountProxy
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object AccountProxyInjector : KoinComponent {
    fun getAccountProxy(): AccountProxy = get()
}