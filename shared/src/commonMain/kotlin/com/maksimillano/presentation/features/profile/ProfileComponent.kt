package com.maksimillano.presentation.features.profile

import com.maksimillano.presentation.base.mvi.BaseMviComponent
import com.maksimillano.presentation.base.ContextWrapper

abstract class ProfileComponent(contextWrapper: ContextWrapper) : BaseMviComponent<ProfileState, ProfileMviEvent, ProfileNavEvent, ProfileViewAction>(contextWrapper)