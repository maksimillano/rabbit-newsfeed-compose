package com.maksimillano.android

import android.app.Application
import com.maksimillano.appContextHolder
import com.maksimillano.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsfeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContextHolder = this

        startKoin {
            androidContext(this@NewsfeedApplication)
            androidLogger()
            modules(appModule)
        }
    }
}