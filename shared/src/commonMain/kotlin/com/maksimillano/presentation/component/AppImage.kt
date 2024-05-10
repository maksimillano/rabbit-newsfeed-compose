package com.maksimillano.presentation.component

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun AppImage(
    uri: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    onLoading: @Composable (BoxScope.(Float) -> Unit)? = null,
    onFailure: @Composable (BoxScope.(Throwable) -> Unit)? = null,
    contentAlignment: Alignment = Alignment.Center,
    animationSpec: FiniteAnimationSpec<Float>? = null,
) {
    if (LocalInspectionMode.current) {
        Box(modifier = modifier)
    } else {
        val painter = asyncPainterResource(
            data = uri
        )
        KamelImage(
            painter,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter,
            onLoading,
            onFailure,
            contentAlignment,
            animationSpec
        )
    }
}

@Composable
fun AppImage(
    resource: ImageResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    if (LocalInspectionMode.current) {
        Box(modifier = modifier)
    } else {
        val painter = painterResource(
            imageResource = resource
        )
        Image(
            painter,
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )
    }
}