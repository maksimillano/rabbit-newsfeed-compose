package com.maksimillano.presentation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext

class ContextWrapperImpl(
    override val componentContext: ComponentContext,
    override val navigationOwner: NavigationOwner
) : ContextWrapper {
    override fun childWrapper(key: String): ContextWrapper {
        return ContextWrapperImpl(componentContext.childContext(key), navigationOwner)
    }
}