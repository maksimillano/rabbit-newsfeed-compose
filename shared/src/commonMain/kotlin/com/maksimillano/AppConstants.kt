package com.maksimillano

import io.github.z4kn4fein.semver.Version
import io.github.z4kn4fein.semver.toVersion

object AppConstants {
    const val ACCOUNTS_COUNT = 3
    const val ANONYMOUS_ACCOUNT_NUMBER = 0
    const val THEME_ANIMATION_DURATION = 500
    const val DATA_FILES_PREFIX = "rabbit"
    const val LOGGER_TAG = "RabbitApp"
    val version: Version =  BuildKonfig.VERSION.toVersion()
}