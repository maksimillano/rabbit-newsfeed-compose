package com.maksimillano.presentation.features.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.maksimillano.presentation.base.ViewEntry

interface MainComponent {
    val state: Value<ChildStack<MainAppEntry, ViewEntry>>
    fun openComments()
}