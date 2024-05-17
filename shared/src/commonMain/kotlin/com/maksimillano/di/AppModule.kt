package com.maksimillano.di

import com.maksimillano.api.domain.features.navigation.NavigationDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory
import com.maksimillano.api.domain.features.theme.ThemeSupplier
import com.maksimillano.api.domain.preference.UserPreferences
import com.maksimillano.impl.data.features.navigation.NavigationDependenciesImpl
import com.maksimillano.impl.data.features.newsfeed.NewsFeedDependenciesImpl
import com.maksimillano.impl.data.features.theme.ThemeSupplierImpl
import com.maksimillano.impl.data.preference.UserPreferencesImpl
import com.maksimillano.presentation.features.navigation.NavigationViewModel
import com.maksimillano.presentation.features.navigation.NavigationViewModelImpl
import com.maksimillano.presentation.features.newsfeed.DefaultFeedDisplayEntryFactory
import com.maksimillano.presentation.features.newsfeed.ImageSizeCalculator
import com.maksimillano.presentation.features.newsfeed.ImageSizeCalculatorImpl
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModel
import com.maksimillano.presentation.features.newsfeed.NewsFeedViewModelImpl
import com.maksimillano.presentation.features.newsfeed.TextCalculator
import com.maksimillano.presentation.features.newsfeed.TextCalculatorImpl
import org.koin.dsl.module

val appModule = module {
    single<UserPreferences> { UserPreferencesImpl() }
    single<ThemeSupplier> { ThemeSupplierImpl(get()) }

    factory<ImageSizeCalculator> { params ->
        ImageSizeCalculatorImpl(params.component1())
    }
    factory<TextCalculator> { params ->
        val config = TextCalculatorImpl.TextCalculatorConfig(
            textMeasurer = params.component2(),
            widthProvider = params.component1(),
            textStyleProvider = params.component3(),
            maxLineEntry = 4
        )
        TextCalculatorImpl(config)
    }
    factory<NewsFeedDisplayEntryFactory> { params ->
        DefaultFeedDisplayEntryFactory(get(parameters = { params }), get(parameters = { params }))
    }
    factory<NewsFeedDependencies> { params ->
        NewsFeedDependenciesImpl(get(parameters = { params }))
    }
    factory<NavigationDependencies> { NavigationDependenciesImpl(get(), get()) }

    factory<NewsFeedViewModel> { params ->
        NewsFeedViewModelImpl(get(parameters = { params }))
    }
    factory<NavigationViewModel> { NavigationViewModelImpl(get()) }
}