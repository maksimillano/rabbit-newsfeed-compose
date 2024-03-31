package com.maksimillano.presentation.features.launch

interface FeedCallback {
    fun onCommentOpen(commentId: String)
    fun onStoryOpen(storyId: String)
}