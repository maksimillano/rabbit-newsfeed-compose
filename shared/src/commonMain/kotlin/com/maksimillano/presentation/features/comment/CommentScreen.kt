package com.maksimillano.presentation.features.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CommentScreen(commentComponent: CommentComponent) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xff585871))
            .clickable { commentComponent.navigator.onButtonBack() }
    ) {
        Text("${CommentComponentImpl::class.simpleName}", modifier = Modifier.align(Alignment.Center))
    }
}