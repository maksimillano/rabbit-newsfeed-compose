package com.maksimillano.presentation.features.comment

import com.arkivanov.decompose.ComponentContext
import com.maksimillano.presentation.features.main.MainComponent

class CommentComponentImpl(
    componentContext: ComponentContext,
    override val mainComponent: MainComponent
) : CommentComponent, ComponentContext by componentContext {

    companion object {
        const val TRANSACTION_DURATION = 500
    }
}