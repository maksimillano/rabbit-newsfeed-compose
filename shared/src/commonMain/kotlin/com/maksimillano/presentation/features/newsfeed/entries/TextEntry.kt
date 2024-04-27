package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maksimillano.api.model.post.newfeed.Feed
import com.maksimillano.presentation.component.backgroundThemed
import com.maksimillano.presentation.component.colorThemed
import com.maksimillano.presentation.component.dimenThemed

data class TextEntry(
    private val text: String,
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "Text${feedItem.sourceId}-${feedItem.date}"

    @Composable
    override fun onBind() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .backgroundThemed { it.backgroundBarColor }
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = text,
                color = colorThemed { it.textColor },
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .backgroundThemed { it.backgroundBarColor },
                fontSize = dimenThemed { it.postContent }
            )
        }
    }
}