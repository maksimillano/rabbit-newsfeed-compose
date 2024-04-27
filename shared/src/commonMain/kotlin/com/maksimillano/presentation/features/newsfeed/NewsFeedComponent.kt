package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class NewsFeedComponent(contextWrapper: ContextWrapper)
    : BaseMviComponent<NewsFeedState, NewsFeedMviEvent, NewsFeedNavEvent, NewsFeedViewAction>(contextWrapper)