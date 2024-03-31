package com.maksimillano.di

import com.maksimillano.api.preference.UserPreferences
import com.maksimillano.api.proxy.AccountProxy
import com.maksimillano.api.proxy.newsfeed.NewsFeedLoader
import com.maksimillano.data.proxy.AccountProxyImpl
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModel
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModelImpl
import org.koin.dsl.module

val appModule = module {
    single<AccountProxy> { AccountProxyImpl() }

//    single<UserPreferences> { UserPreferences() }
//    factory<NewsFeedLoader> { DefaultNewsFeedLoader() }
//    factory<NewsFeedViewModel> { NewsFeedViewModelImpl(get(), get()) }
}