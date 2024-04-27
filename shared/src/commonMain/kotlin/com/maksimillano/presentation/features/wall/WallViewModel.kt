package com.maksimillano.presentation.features.wall

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class WallViewModel(initState: WallState) : BaseMviViewModel<WallState, WallMviEvent, WallNavEvent, WallViewAction>(initState)