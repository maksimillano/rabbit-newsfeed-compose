package com.maksimillano.presentation.features.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CommentScreen(commentComponent: CommentComponent) {
    Box(
        Modifier
            .fillMaxSize()
//            .clickableNoRipple {
//                commentComponent.mainComponent.openComments()
////                sendEvent( TODO("ShiftStackAnimator")
////                    StackEvent.Pop(ShiftStackAnimator())
////                )
//            }
            .background(Color(0xff87a8de))
    ) {
        Text("Commented block", Modifier.align(Alignment.Center), fontSize = 22.sp)
    }
}