package com.maksimillano.presentation.base

import com.arkivanov.decompose.ComponentContext

interface ContextWrapper {
    val componentContext: ComponentContext
    val navigationOwner: NavigationOwner
    fun childWrapper(key: String): ContextWrapper
}