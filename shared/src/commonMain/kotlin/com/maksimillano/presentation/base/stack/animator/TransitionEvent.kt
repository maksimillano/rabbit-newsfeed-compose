package com.maksimillano.presentation.base.stack.animator

sealed interface TransitionEvent<T : Any> {
    val animator: StackAnimator<T>
    data class InBegin<T : Any>(override val animator: StackAnimator<T>) : TransitionEvent<T>
    data class InEnd<T : Any>(override val animator: StackAnimator<T>) : TransitionEvent<T>
    data class OutBegin<T : Any>(override val animator: StackAnimator<T>) : TransitionEvent<T>
    data class OutEnd<T : Any>(override val animator: StackAnimator<T>) : TransitionEvent<T>
    data class Cancelled<T : Any>(override val animator: StackAnimator<T>) : TransitionEvent<T>
}

sealed interface StackEvent<T : Any> {
    val animator: StackAnimator<T>
    data class Push<T : Any>(
        val entry: T,
        override val animator: StackAnimator<T> = SimpleStackAnimator()
    ) : StackEvent<T>
    data class Pop<T : Any>(override val animator: StackAnimator<T> = SimpleStackAnimator()) :
        StackEvent<T>
}

sealed interface StackState<T : Any> {
    val animator: StackAnimator<T>
    data class Settled<T : Any>(override val animator: StackAnimator<T>) : StackState<T>
    data class In<T : Any>(override val animator: StackAnimator<T>) : StackState<T>
    data class Out<T : Any>(override val animator: StackAnimator<T>) : StackState<T>
}