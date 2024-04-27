package com.maksimillano.presentation.base

import com.arkivanov.decompose.ComponentContext

abstract class BaseComponentOld(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

//    init {
//        subscribeOnLifecycle()
//    }
//
//    private fun subscribeOnLifecycle() {
//        lifecycle.subscribe(this)
//    }
//
//    override fun onDestroy() {
//        lifecycle.unsubscribe(this)
//    }
}