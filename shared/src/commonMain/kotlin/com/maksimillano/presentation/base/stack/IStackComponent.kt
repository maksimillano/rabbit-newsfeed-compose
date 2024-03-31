package com.maksimillano.presentation.base.stack

import com.maksimillano.presentation.base.stack.animator.StackState
import kotlinx.coroutines.flow.StateFlow

interface IStackComponent<ENTRY : Any> {
    val stackState: StateFlow<StackState<ENTRY>>
    val stackNavigator: OnStackNavigator<ENTRY>
}