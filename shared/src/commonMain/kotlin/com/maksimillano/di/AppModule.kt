package com.maksimillano.di

import com.maksimillano.api.domain.features.navigation.NavigationDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.theme.ThemeSupplier
import com.maksimillano.api.domain.preference.UserPreferences
import com.maksimillano.impl.data.features.navigation.NavigationDependenciesImpl
import com.maksimillano.impl.data.features.newsfeed.NewsFeedDependenciesImpl
import com.maksimillano.impl.data.features.theme.ThemeSupplierImpl
import com.maksimillano.impl.data.preference.UserPreferencesImpl
import com.maksimillano.presentation.features.navigation.NavigationViewModel
import com.maksimillano.presentation.features.navigation.NavigationViewModelImpl
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModel
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModelImpl
import org.koin.dsl.module

val appModule = module {
    single<UserPreferences> { UserPreferencesImpl() }
    single<ThemeSupplier> { ThemeSupplierImpl(get()) }

    factory<NewsFeedDependencies> { NewsFeedDependenciesImpl() }
    factory<NavigationDependencies> { NavigationDependenciesImpl(get(), get()) }

    factory<NewsFeedViewModel> { NewsFeedViewModelImpl(get()) }
    factory<NavigationViewModel> { NavigationViewModelImpl(get()) }
}