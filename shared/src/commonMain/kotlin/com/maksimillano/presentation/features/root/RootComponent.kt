package com.maksimillano.presentation.features.root

import com.maksimillano.presentation.features.root.impl.LargeRootComponent
import kotlinx.coroutines.flow.StateFlow

interface RootComponent {
    val state: StateFlow<LargeRootComponent.LargeMainChildren>
}