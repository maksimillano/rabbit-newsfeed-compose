package com.maksimillano.presentation.features.wall

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class WallComponent(contextWrapper: ContextWrapper) : BaseMviComponent<WallState, WallMviEvent, WallNavEvent, WallViewAction>(contextWrapper)