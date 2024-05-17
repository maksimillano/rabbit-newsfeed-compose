package com.maksimillano.presentation.features.newsfeed

interface TextCalculator {
    fun calculate(text: String): List<TextResult>
    data class TextResult(
        val text: String,
        val height: Int
    )
}