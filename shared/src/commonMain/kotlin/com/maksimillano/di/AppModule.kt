package com.maksimillano.di

import com.maksimillano.api.data.account.AccountLoader
import com.maksimillano.api.data.posts.PostsLoader
import com.maksimillano.api.data.newsfeed.NewsFeedDependencies
import com.maksimillano.api.data.theme.ThemeProvider
import com.maksimillano.api.data.preference.UserPreferences
import com.maksimillano.impl.data.account.AccountLoaderImpl
import com.maksimillano.impl.data.preference.UserPreferencesImpl
import com.maksimillano.impl.data.newsfeed.DefaultNewsFeedLoader
import com.maksimillano.impl.data.newsfeed.NewsFeedDependenciesImpl
import com.maksimillano.impl.data.theme.ThemeProviderImpl
import com.maksimillano.presentation.features.navigation.NavigationViewModel
import com.maksimillano.presentation.features.navigation.NavigationViewModelImpl
import com.maksimillano.presentation.features.newsfeed.FeedDisplayEntryFactory
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModel
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModelImpl
import org.koin.dsl.module

val appModule = module {
    single<UserPreferences> { UserPreferencesImpl() }
    single<ThemeProvider> { ThemeProviderImpl(get()) }
    single<AccountLoader> { AccountLoaderImpl() }

    factory<PostsLoader> { DefaultNewsFeedLoader() }
    factory<FeedDisplayEntryFactory> { FeedDisplayEntryFactory() }
    factory<NewsFeedDependencies> { NewsFeedDependenciesImpl() }

    factory<NewsFeedViewModel> { NewsFeedViewModelImpl(get(), get(), get()) }
    factory<NavigationViewModel> { NavigationViewModelImpl(get(), get()) }
}