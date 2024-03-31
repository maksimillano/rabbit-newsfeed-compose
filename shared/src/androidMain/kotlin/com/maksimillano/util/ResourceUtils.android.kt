package com.maksimillano.util

import com.maksimillano.appContextHolder
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

actual fun stringValue(key: StringResource): String {
    return StringDesc.Resource(key).toString(appContextHolder)
}