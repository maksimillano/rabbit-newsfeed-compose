package com.maksimillano.presentation.base.mvi

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.ContextWrapper
import kotlinx.coroutines.*

abstract class BaseMviComponent<STATE : MviState, EVENT : MviEvent, NAVIGATION: MviNavigationEvent, VIEW_ACTION: MviViewAction>(
    contextWrapper: ContextWrapper
) : MviComponent<STATE, EVENT, NAVIGATION, VIEW_ACTION>, BaseComponent(contextWrapper) {
    protected val componentScope = CoroutineScope(Dispatchers.Main)
    private var navigationJob: Job? = null
    private val router: MviRouter<NAVIGATION> by lazy { createRouter() }

    init {
        lifecycle.subscribe(object : Lifecycle.Callbacks {
            override fun onCreate() = this@BaseMviComponent.onCreate()
            override fun onStart() = this@BaseMviComponent.onStart()
            override fun onResume() = this@BaseMviComponent.onResume()
            override fun onPause() = this@BaseMviComponent.onPause()
            override fun onStop() = this@BaseMviComponent.onStop()
            override fun onDestroy() = this@BaseMviComponent.onDestroy()
        })
    }

    protected open fun onCreate() {
    }

    protected open fun onStart() {
        navigationJob = componentScope.launch {
            viewModel.navigationEvents
                .collect { navigation ->
                    router.onHandleNavigationEvent(navigation)
                }
        }
    }

    protected open fun onResume() {
    }

    protected open fun onPause() {
    }

    protected open fun onStop() {
        navigationJob?.cancel()
    }

    protected open fun onDestroy() {
        componentScope.cancel()
    }
}