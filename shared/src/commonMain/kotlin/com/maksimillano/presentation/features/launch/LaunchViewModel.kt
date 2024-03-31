package com.maksimillano.presentation.features.launch

import kotlinx.coroutines.flow.*
import com.maksimillano.presentation.base.ViewModel

class LaunchViewModel : ViewModel {
    private val stateProducer: MutableStateFlow<LaunchState> = MutableStateFlow(LaunchState.Loading)
    val state: StateFlow<LaunchState> = stateProducer
        .onStart { loadAccountInfo() }
        .stateIn(this, SharingStarted.Lazily, LaunchState.Loading) // TODO: Research Lazily arg

    private fun loadAccountInfo() {
//        launch {
//            engine.engineUser
//                .collect { user ->
//                    stateProducer.emit(LaunchState.Loaded(user))
//                }
//        }
    }

    fun updateTheme() {
//        val currentTheme = engine.theme.value
//        val newTheme = if (currentTheme == MainDarkTheme) {
//            MainLightTheme
//        } else {
//            MainDarkTheme
//        }
//        val cmd = UpdateThemeCmd(newTheme)
//        launch {
//            engine.executeCmd(cmd)
//        }
    }

    fun logout() {
    }
}