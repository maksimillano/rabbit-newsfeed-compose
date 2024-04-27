package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class NewsFeedViewModel(initState: NewsFeedState) : BaseMviViewModel<NewsFeedState, NewsFeedMviEvent, NewsFeedNavEvent, NewsFeedViewAction>(initState)