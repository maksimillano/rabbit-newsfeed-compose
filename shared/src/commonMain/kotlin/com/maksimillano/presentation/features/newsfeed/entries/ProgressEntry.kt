package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.component.colorThemed

data class ProgressEntry(
    private val gravity: Gravity
) : FeedDisplayEntry {

    override val key: String = "Progress$gravity"
    @Composable
    override fun onBind() {
        Box(
            modifier = Modifier.height(50.dp)
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                color = colorThemed { it.accentColor },
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    enum class Gravity {
        TOP, BOTTOM
    }
}