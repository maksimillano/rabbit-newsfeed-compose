package com.maksimillano.presentation.base.stack

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.maksimillano.presentation.base.BaseComponent
import com.maksimillano.presentation.base.stack.animator.TransitionEvent
import com.maksimillano.presentation.base.stack.animator.SimpleStackAnimator
import com.maksimillano.presentation.base.stack.animator.StackState
import com.maksimillano.presentation.base.stack.animator.StackEvent
import kotlinx.coroutines.flow.*

abstract class StackComponent<ENTRY : Any>(
    componentContext: ComponentContext,
) : BaseComponent(componentContext),
    IStackComponent<ENTRY> {
    protected open val navigation = StackNavigation<ENTRY>()
    protected abstract val stack: Value<ChildStack<ENTRY, BaseComponent>>

    private val stackStateProducer: MutableStateFlow<StackState<ENTRY>> = MutableStateFlow(
        StackState.Settled(
        SimpleStackAnimator()
    ))
    override val stackState: StateFlow<StackState<ENTRY>> = stackStateProducer

//    private val stackEventProducer: MutableSharedFlow<StackEvent<ENTRY>> = MutableSharedFlow()
    override val stackNavigator: OnStackNavigator<ENTRY> = OnStackNavigator { stackEvent ->
        val animator = stackEvent.animator
        when (stackEvent) {
            is StackEvent.Push -> {
                navigation.push(stackEvent.entry) {
                    stackStateProducer.tryEmit(StackState.In(animator))
                }
            }
            is StackEvent.Pop -> {
                stackStateProducer.tryEmit(StackState.Out(animator))
            }
        }
    }

//    private val animatorListener: MutableSharedFlow<AnimatorEvent<ENTRY>> = MutableSharedFlow()
    private val transitionEventProducer: AnimatorEventProducer<ENTRY> = AnimatorEventProducer { animatorEvent ->
        val animator = animatorEvent.animator
        when (animatorEvent) {
            is TransitionEvent.InBegin -> {
                //navigation.push()
                stackStateProducer.tryEmit(StackState.In(animator))
            }
            is TransitionEvent.InEnd -> {
                stackStateProducer.tryEmit(StackState.Settled(animator))
            }
            is TransitionEvent.OutBegin -> {
                stackStateProducer.tryEmit(StackState.Out(animator))
            }
            is TransitionEvent.OutEnd -> {
                navigation.pop {
                    stackStateProducer.tryEmit(StackState.Settled(animator))
                }
            }
            is TransitionEvent.Cancelled -> {
                stackStateProducer.tryEmit(StackState.Settled(animator))
            }
        }
    }

//    @Composable
//    override fun render() {
//        val state by stackState.collectAsState()
//        val animator = state.animator
//
//        animator.renderStack(state, stack.items, transitionEventProducer)
//    }
}