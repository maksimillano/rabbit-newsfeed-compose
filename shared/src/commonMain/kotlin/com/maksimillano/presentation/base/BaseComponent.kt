package com.maksimillano.presentation.base

import com.arkivanov.decompose.ComponentContext

abstract class BaseComponent(
    contextWrapper: ContextWrapper
) : RenderComponent,
    NavigationOwner by contextWrapper.navigationOwner,
    ComponentContext by contextWrapper.componentContext