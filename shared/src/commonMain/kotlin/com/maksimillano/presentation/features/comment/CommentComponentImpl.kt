package com.maksimillano.presentation.features.comment

import androidx.compose.runtime.Composable
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.maksimillano.presentation.base.mvi.MviRouter
import com.maksimillano.presentation.base.ContextWrapper

class CommentComponentImpl(contextWrapper: ContextWrapper) : CommentComponent(contextWrapper) {
    override val viewModel: CommentsViewModel = instanceKeeper.getOrCreate { CommentsViewModelImpl() }

    override fun createRouter(): MviRouter<CommentsNavEvent> = MviRouter {
    }

    @Composable
    override fun render() = CommentScreen(this)
}