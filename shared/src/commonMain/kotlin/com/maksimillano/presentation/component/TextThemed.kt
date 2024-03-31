package com.maksimillano.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.maksimillano.api.theme.ColorScheme

@Composable
fun TextThemed(
    text: String,
    colorProvider: (ColorScheme) -> Color,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {
//    val currentTheme by EngineProvider.get().theme.collectAsState()
//
//    val textColor by animateColorAsState(
//        colorProvider(currentTheme.colorScheme),
//        animationSpec = tween(
//            durationMillis = 500
//        )
//    )
//
//    Text(
//        text,
//        modifier,
//        textColor,
//        fontSize,
//        fontStyle,
//        fontWeight,
//        fontFamily,
//        letterSpacing,
//        textDecoration,
//        textAlign,
//        lineHeight,
//        overflow,
//        softWrap,
//        maxLines,
//        minLines,
//        onTextLayout,
//        style
//    )
}