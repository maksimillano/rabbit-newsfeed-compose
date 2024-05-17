package com.maksimillano

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.impl.data.features.newsfeed.MockFeedGenerator
import com.maksimillano.presentation.component.AppTheme
import com.maksimillano.presentation.features.newsfeed.entries.HeaderNewsfeedEntry

//
//@OptIn(ExperimentalResourceApi::class)
//@Composable
//@Preview
//fun HeaderEntryPreview() {
//    val post = MockFeedGenerator.generate().feeds.first()
//
//    HeaderEntry(
//        title = "12345",
//        iconUrl = "stock1.jpg",
//        dateFormatted = "3 days ago",
//        date = 100000,
//        post = post as Post
//    ).onBind()
//}

@Composable
@Preview
fun HeaderEntryPreview() {
    AppTheme {
        val post = MockFeedGenerator.generate(0).feeds.first()

        HeaderNewsfeedEntry(
            title = "12345",
            iconUrl = "stock1.jpg",
            dateFormatted = "3 days ago",
            date = 100000,
            post = post as Post
        ).onBind()
    }
}
