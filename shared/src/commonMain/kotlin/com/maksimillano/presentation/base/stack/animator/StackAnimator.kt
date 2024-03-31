package com.maksimillano.presentation.base.stack.animator

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.Child
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.stack.AnimatorEventProducer

interface StackAnimator<ENTRY : Any> {
    @Composable
    fun renderStack(
        stackState: StackState<ENTRY>,
        stack: List<Child.Created<ENTRY, BaseComponent>>,
        animatorEventProducer: AnimatorEventProducer<ENTRY>
    )
}
