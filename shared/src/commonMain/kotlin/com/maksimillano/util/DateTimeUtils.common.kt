package com.maksimillano.util

import com.maksimillano.MR
import korlibs.time.fromDays
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration

expect fun timestampMs(): Long

fun timeToString(timestampMs: Long): String {
    val minutes = timestampMs / 1000 / 60
    val hours = minutes / 24

    val m = minutes % 60
    val h = hours % 24

    val mm = if (m < 10) {
        "0$m"
    } else {
        m.toString()
    }
    val hh = if (h < 10) {
        "0$h"
    } else {
        h.toString()
    }
    return "$hh:$mm"
}

fun Long.toFormattedTime(): String {
    val currentMoment = Clock.System.now()
    val sourceMoment = Instant.fromEpochSeconds(this)
    val duration = currentMoment - sourceMoment
    return when {
        duration.inWholeDays >= 365 -> {
            "23 янв 2023"
        }
        duration.inWholeDays >= 30 -> {
            "1 янв"
        }
        duration.inWholeDays >= 7 -> {
            val weeks = duration.inWholeDays / 7
            "$weeks нед"
        }
        duration.inWholeDays >= 1 -> {
            val days = duration.inWholeDays
            "$days д"
        }
        duration.inWholeHours >= 1 -> {
            val hours = duration.inWholeHours
            "$hours ч"
        }
        duration.inWholeMinutes >= 1 -> {
            val minutes = duration.inWholeMinutes
            "$minutes мин"
        }
        else -> {
            stringValue(MR.strings.time_right_now)
        }
    }
}

fun daysAgoUnixTime(days: Int = 1): Long {
    return Clock.System.now().minus(Duration.fromDays(days)).epochSeconds
}
