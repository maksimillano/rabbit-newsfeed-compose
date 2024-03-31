package com.maksimillano.android

import android.app.Application
import com.maksimillano.appContextHolder

class NewsfeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContextHolder = this
    }
}