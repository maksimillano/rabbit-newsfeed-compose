package com.maksimillano.presentation.features.navigation

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class NavigationViewModel(initState: NavigationState) : BaseMviViewModel<NavigationState, NavigationMviEvent, NavigationNavEvent, NavigationViewAction>(initState)