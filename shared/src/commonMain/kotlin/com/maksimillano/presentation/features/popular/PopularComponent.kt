package com.maksimillano.presentation.features.popular

import com.maksimillano.presentation.base.AppNavigator

interface PopularComponent {
    val viewModel: PopularViewModel
    val appRouter: AppNavigator
}