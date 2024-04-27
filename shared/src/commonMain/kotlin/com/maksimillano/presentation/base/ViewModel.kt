package com.maksimillano.presentation.base

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext

interface ViewModel : CoroutineScope, InstanceKeeper.Instance {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    override fun onDestroy() {
        if (isActive) {
             cancel()
        }
    }
}