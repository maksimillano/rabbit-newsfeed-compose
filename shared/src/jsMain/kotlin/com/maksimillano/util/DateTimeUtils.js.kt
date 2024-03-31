package com.maksimillano.util

import kotlin.js.Date

actual fun timestampMs(): Long {
    return Date.now().toLong()
}
