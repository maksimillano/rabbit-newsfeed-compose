package com.maksimillano.presentation.features.navigation

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class NavigationComponent(contextWrapper: ContextWrapper) : BaseMviComponent<NavigationState, NavigationMviEvent, NavigationNavEvent, NavigationViewAction>(contextWrapper)