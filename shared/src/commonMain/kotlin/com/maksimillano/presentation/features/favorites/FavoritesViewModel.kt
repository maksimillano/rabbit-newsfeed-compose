package com.maksimillano.presentation.features.favorites

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class FavoritesViewModel(initState: FavoritesState) : BaseMviViewModel<FavoritesState, FavoritesMviEvent, FavoritesNavigationEvent, FavoriteViewAction>(initState)