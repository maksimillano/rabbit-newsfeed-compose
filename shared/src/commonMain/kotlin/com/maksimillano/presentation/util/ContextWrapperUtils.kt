package com.maksimillano.presentation.util

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.maksimillano.presentation.base.ContextWrapper
import com.maksimillano.presentation.base.ContextWrapperImpl
import com.maksimillano.presentation.base.NavigationOwner

fun ComponentContext.createContextWrapper(key: String, navigationOwner: NavigationOwner): ContextWrapper {
    return ContextWrapperImpl(childContext(key), navigationOwner)
}