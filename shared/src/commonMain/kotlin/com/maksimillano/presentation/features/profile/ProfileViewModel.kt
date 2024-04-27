package com.maksimillano.presentation.features.profile

import com.maksimillano.presentation.base.mvi.BaseMviViewModel

abstract class ProfileViewModel(initState: ProfileState) : BaseMviViewModel<ProfileState, ProfileMviEvent, ProfileNavEvent, ProfileViewAction>(initState)