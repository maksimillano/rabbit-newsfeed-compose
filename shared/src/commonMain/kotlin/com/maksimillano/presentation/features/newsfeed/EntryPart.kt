package com.maksimillano.presentation.features.newsfeed

sealed interface EntryPart {
    data object Full : EntryPart
    data object Top : EntryPart
    data object Bottom : EntryPart
    data class Middle(val position: Int) : EntryPart
}