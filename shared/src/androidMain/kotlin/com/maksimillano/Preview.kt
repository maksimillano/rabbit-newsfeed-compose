package com.maksimillano

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import com.maksimillano.util.clickableNoRipple
import korlibs.io.async.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class SampleNode(
    var number: Int
) : Modifier.Node(), ParentDataModifierNode {
    override fun onAttach() {
        coroutineScope.launch {

        }
        println()
    }
    override fun onDetach() {
        println()
    }

    override fun Density.modifyParentData(parentData: Any?): Any {
        return "Fucker"
    }
}
class SampleElement(val number: Int) : ModifierNodeElement<SampleNode>() {
    override fun create(): SampleNode = SampleNode(0)
    override fun equals(other: Any?): Boolean = number == other
    override fun hashCode(): Int = number.hashCode()
    override fun update(node: SampleNode) {
        node.number = number
    }
}

@Stable
fun Modifier.sample(number: Int): Modifier {
    return this then SampleElement(number)
}

@Stable
fun Modifier.inline(): Modifier {
    return layout { measurebles, constraints ->

        val placebles = measurebles.measure(constraints)
        layout(constraints.maxWidth, constraints.maxHeight) {
            placebles.place(100, 100)
        }
    }
}

@Composable
fun ColumnLegend(
    modifier: Modifier = Modifier,
    content: @Composable @UiComposable () -> Unit
) {
    Layout(
        content,
        modifier,
    ) { measureables, contraints ->
        val measureble = measureables.first()
        val parentData = measureble.parentData
        val width = measureble.minIntrinsicWidth(200)
        val childConstraint = Constraints.fixed(width, 200)
        val placeble = measureble.measure(childConstraint)
        val realWidth = placeble.width
        val realHeight = placeble.height
        layout(contraints.maxWidth, contraints.maxHeight) {
            placeble.place(0, 0)
        }
    }
}

val testScope = CoroutineScope(Dispatchers.Default)
val themeColor = MutableStateFlow("start")

var isDark = false

@Composable
fun Header(modifier: Modifier = Modifier, isChanged: MutableState<Boolean>) {
    var state by remember {
        mutableStateOf(1)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            state++
            println("Shitty 2")
        }
    }
    println("Shitty")
    Box(modifier = modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        println("Shitty 3")
        Text(
            text = "counter: $state",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.clickableNoRipple {
                isDark = !isDark
                isChanged.component2().invoke(isDark)
            }
        )
    }
}

@Composable
fun animateColor(target: Color): Color {
    return animateColorAsState(
        targetValue = target,
        animationSpec = tween(500),
        label = ""
    ).value
}

@Composable
@Preview()
fun HeaderPreview() {
    val isChanged = remember {
        mutableStateOf(false)
    }
    val pallete = if (isChanged.component1()) darkScheme else lightScheme
    MaterialTheme(
        colorScheme = pallete.withSwitch(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Header(
                modifier = Modifier
                    .weight(1f),
                isChanged = isChanged
            )
            Header(
                modifier = Modifier
                    .weight(1f),
                isChanged = isChanged
            )
        }
    }
}

val darkScheme = ColorScheme(
    primary = Color(0xFF9A44B0),
    primaryContainer = Color(0xFF9A44B0),
    onPrimary = Color(0xFFF5D2FF),
    onPrimaryContainer = Color(0xFF9A44B0),
    secondary = Color(0xFF9EACFA),
    secondaryContainer = Color(0xFF9EACFA),
    onSecondary = Color(0xFF9EACFA),
    onSecondaryContainer = Color(0xFF9EACFA),
    tertiary = Color(0xFF9EACFA),
    tertiaryContainer = Color(0xFF9EACFA),
    onTertiary = Color(0xFF9EACFA),
    onTertiaryContainer = Color(0xFF9EACFA),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFFB67F7F),

    surface = Color(0xFF393939),
    surfaceVariant = Color(0xFFD5D5D5),
    surfaceTint = Color(0xFFF44336),
    onSurface = Color(0xFF9C9C9C),
    onSurfaceVariant = Color(0xFFF44336),

    outline = Color(0xFF9C9C9C),
    outlineVariant = Color(0xFF9C9C9C),

    error = Color(0xFFF44336),
    errorContainer = Color(0xFFF44336),
    onError = Color(0xFFFFE5E3),
    onErrorContainer = Color(0xFFF44336),

    inverseSurface = Color(0xFFF44336),
    inversePrimary = Color(0xFFF44336),
    inverseOnSurface = Color(0xFFF44336),

    scrim = Color(0xFFF44336)
)

val lightScheme = ColorScheme(
    primary = Color(0xFF9A44B0),
    primaryContainer = Color(0xFF9A44B0),
    onPrimary = Color(0xFFC900FF),
    onPrimaryContainer = Color(0xFF9A44B0),
    secondary = Color(0xFF9EACFA),
    secondaryContainer = Color(0xFF9EACFA),
    onSecondary = Color(0xFF9EACFA),
    onSecondaryContainer = Color(0xFF9EACFA),
    tertiary = Color(0xFF9EACFA),
    tertiaryContainer = Color(0xFF9EACFA),
    onTertiary = Color(0xFF9EACFA),
    onTertiaryContainer = Color(0xFF9EACFA),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFFB67F7F),

    surface = Color(0xFFF4F4F4),
    surfaceVariant = Color(0xFFD5D5D5),
    surfaceTint = Color(0xFFF44336),
    onSurface = Color(0xFF9C9C9C),
    onSurfaceVariant = Color(0xFFF44336),

    outline = Color(0xFF9C9C9C),
    outlineVariant = Color(0xFF9C9C9C),

    error = Color(0xFFF44336),
    errorContainer = Color(0xFFF44336),
    onError = Color(0xFFFFE5E3),
    onErrorContainer = Color(0xFFF44336),

    inverseSurface = Color(0xFFF44336),
    inversePrimary = Color(0xFFF44336),
    inverseOnSurface = Color(0xFFF44336),

    scrim = Color(0xFFF44336)
)

@Composable
fun ColorScheme.withSwitch() = copy(
    surface = animateColor(surface),
    onPrimary = animateColor(onPrimary)
)















//
//
//
//
//
//val primaryLight = Color(0xFF6F5289)
//val onPrimaryLight = Color(0xFFFFFFFF)
//val primaryContainerLight = Color(0xFFF1DBFF)
//val onPrimaryContainerLight = Color(0xFF290C41)
//val secondaryLight = Color(0xFF665A6F)
//val onSecondaryLight = Color(0xFFFFFFFF)
//val secondaryContainerLight = Color(0xFFEDDDF6)
//val onSecondaryContainerLight = Color(0xFF211829)
//val tertiaryLight = Color(0xFF805157)
//val onTertiaryLight = Color(0xFFFFFFFF)
//val tertiaryContainerLight = Color(0xFFFFD9DC)
//val onTertiaryContainerLight = Color(0xFF321016)
//val errorLight = Color(0xFFBA1A1A)
//val onErrorLight = Color(0xFFFFFFFF)
//val errorContainerLight = Color(0xFFFFDAD6)
//val onErrorContainerLight = Color(0xFF410002)
//val backgroundLight = Color(0xFFFFF7FE)
//val onBackgroundLight = Color(0xFF1E1A20)
//val surfaceLight = Color(0xFFFFF7FE)
//val onSurfaceLight = Color(0xFF1E1A20)
//val surfaceVariantLight = Color(0xFFE9DFEA)
//val onSurfaceVariantLight = Color(0xFF4B454D)
//val outlineLight = Color(0xFF7C757E)
//val outlineVariantLight = Color(0xFFCDC4CE)
//val scrimLight = Color(0xFF000000)
//val inverseSurfaceLight = Color(0xFF332F35)
//val inverseOnSurfaceLight = Color(0xFFF7EEF6)
//val inversePrimaryLight = Color(0xFFDCB9F8)
//val surfaceDimLight = Color(0xFFE0D8DF)
//val surfaceBrightLight = Color(0xFFFFF7FE)
//val surfaceContainerLowestLight = Color(0xFFFFFFFF)
//val surfaceContainerLowLight = Color(0xFFFAF1F9)
//val surfaceContainerLight = Color(0xFFF4EBF3)
//val surfaceContainerHighLight = Color(0xFFEEE6EE)
//val surfaceContainerHighestLight = Color(0xFFE8E0E8)
//
//val primaryLightMediumContrast = Color(0xFF52366C)
//val onPrimaryLightMediumContrast = Color(0xFFFFFFFF)
//val primaryContainerLightMediumContrast = Color(0xFF8768A1)
//val onPrimaryContainerLightMediumContrast = Color(0xFFFFFFFF)
//val secondaryLightMediumContrast = Color(0xFF4A3F52)
//val onSecondaryLightMediumContrast = Color(0xFFFFFFFF)
//val secondaryContainerLightMediumContrast = Color(0xFF7D7085)
//val onSecondaryContainerLightMediumContrast = Color(0xFFFFFFFF)
//val tertiaryLightMediumContrast = Color(0xFF61373C)
//val onTertiaryLightMediumContrast = Color(0xFFFFFFFF)
//val tertiaryContainerLightMediumContrast = Color(0xFF99676C)
//val onTertiaryContainerLightMediumContrast = Color(0xFFFFFFFF)
//val errorLightMediumContrast = Color(0xFF8C0009)
//val onErrorLightMediumContrast = Color(0xFFFFFFFF)
//val errorContainerLightMediumContrast = Color(0xFFDA342E)
//val onErrorContainerLightMediumContrast = Color(0xFFFFFFFF)
//val backgroundLightMediumContrast = Color(0xFFFFF7FE)
//val onBackgroundLightMediumContrast = Color(0xFF1E1A20)
//val surfaceLightMediumContrast = Color(0xFFFFF7FE)
//val onSurfaceLightMediumContrast = Color(0xFF1E1A20)
//val surfaceVariantLightMediumContrast = Color(0xFFE9DFEA)
//val onSurfaceVariantLightMediumContrast = Color(0xFF464149)
//val outlineLightMediumContrast = Color(0xFF635D66)
//val outlineVariantLightMediumContrast = Color(0xFF7F7882)
//val scrimLightMediumContrast = Color(0xFF000000)
//val inverseSurfaceLightMediumContrast = Color(0xFF332F35)
//val inverseOnSurfaceLightMediumContrast = Color(0xFFF7EEF6)
//val inversePrimaryLightMediumContrast = Color(0xFFDCB9F8)
//val surfaceDimLightMediumContrast = Color(0xFFE0D8DF)
//val surfaceBrightLightMediumContrast = Color(0xFFFFF7FE)
//val surfaceContainerLowestLightMediumContrast = Color(0xFFFFFFFF)
//val surfaceContainerLowLightMediumContrast = Color(0xFFFAF1F9)
//val surfaceContainerLightMediumContrast = Color(0xFFF4EBF3)
//val surfaceContainerHighLightMediumContrast = Color(0xFFEEE6EE)
//val surfaceContainerHighestLightMediumContrast = Color(0xFFE8E0E8)
//
//val primaryLightHighContrast = Color(0xFF301449)
//val onPrimaryLightHighContrast = Color(0xFFFFFFFF)
//val primaryContainerLightHighContrast = Color(0xFF52366C)
//val onPrimaryContainerLightHighContrast = Color(0xFFFFFFFF)
//val secondaryLightHighContrast = Color(0xFF281E30)
//val onSecondaryLightHighContrast = Color(0xFFFFFFFF)
//val secondaryContainerLightHighContrast = Color(0xFF4A3F52)
//val onSecondaryContainerLightHighContrast = Color(0xFFFFFFFF)
//val tertiaryLightHighContrast = Color(0xFF3B171C)
//val onTertiaryLightHighContrast = Color(0xFFFFFFFF)
//val tertiaryContainerLightHighContrast = Color(0xFF61373C)
//val onTertiaryContainerLightHighContrast = Color(0xFFFFFFFF)
//val errorLightHighContrast = Color(0xFF4E0002)
//val onErrorLightHighContrast = Color(0xFFFFFFFF)
//val errorContainerLightHighContrast = Color(0xFF8C0009)
//val onErrorContainerLightHighContrast = Color(0xFFFFFFFF)
//val backgroundLightHighContrast = Color(0xFFFFF7FE)
//val onBackgroundLightHighContrast = Color(0xFF1E1A20)
//val surfaceLightHighContrast = Color(0xFFFFF7FE)
//val onSurfaceLightHighContrast = Color(0xFF000000)
//val surfaceVariantLightHighContrast = Color(0xFFE9DFEA)
//val onSurfaceVariantLightHighContrast = Color(0xFF27222A)
//val outlineLightHighContrast = Color(0xFF464149)
//val outlineVariantLightHighContrast = Color(0xFF464149)
//val scrimLightHighContrast = Color(0xFF000000)
//val inverseSurfaceLightHighContrast = Color(0xFF332F35)
//val inverseOnSurfaceLightHighContrast = Color(0xFFFFFFFF)
//val inversePrimaryLightHighContrast = Color(0xFFF7E6FF)
//val surfaceDimLightHighContrast = Color(0xFFE0D8DF)
//val surfaceBrightLightHighContrast = Color(0xFFFFF7FE)
//val surfaceContainerLowestLightHighContrast = Color(0xFFFFFFFF)
//val surfaceContainerLowLightHighContrast = Color(0xFFFAF1F9)
//val surfaceContainerLightHighContrast = Color(0xFFF4EBF3)
//val surfaceContainerHighLightHighContrast = Color(0xFFEEE6EE)
//val surfaceContainerHighestLightHighContrast = Color(0xFFE8E0E8)
//
//val primaryDark = Color(0xFFDCB9F8)
//val onPrimaryDark = Color(0xFF3F2358)
//val primaryContainerDark = Color(0xFF573A70)
//val onPrimaryContainerDark = Color(0xFFF1DBFF)
//val secondaryDark = Color(0xFFD1C1D9)
//val onSecondaryDark = Color(0xFF372C3F)
//val secondaryContainerDark = Color(0xFF4E4256)
//val onSecondaryContainerDark = Color(0xFFEDDDF6)
//val tertiaryDark = Color(0xFFF3B7BD)
//val onTertiaryDark = Color(0xFF4B252A)
//val tertiaryContainerDark = Color(0xFF653A40)
//val onTertiaryContainerDark = Color(0xFFFFD9DC)
//val errorDark = Color(0xFFFFB4AB)
//val onErrorDark = Color(0xFF690005)
//val errorContainerDark = Color(0xFF93000A)
//val onErrorContainerDark = Color(0xFFFFDAD6)
//val backgroundDark = Color(0xFF151217)
//val onBackgroundDark = Color(0xFFE8E0E8)
//val surfaceDark = Color(0xFF151217)
//val onSurfaceDark = Color(0xFFE8E0E8)
//val surfaceVariantDark = Color(0xFF4B454D)
//val onSurfaceVariantDark = Color(0xFFCDC4CE)
//val outlineDark = Color(0xFF968E98)
//val outlineVariantDark = Color(0xFF4B454D)
//val scrimDark = Color(0xFF000000)
//val inverseSurfaceDark = Color(0xFFE8E0E8)
//val inverseOnSurfaceDark = Color(0xFF332F35)
//val inversePrimaryDark = Color(0xFF6F5289)
//val surfaceDimDark = Color(0xFF151217)
//val surfaceBrightDark = Color(0xFF3C383E)
//val surfaceContainerLowestDark = Color(0xFF100D12)
//val surfaceContainerLowDark = Color(0xFF1E1A20)
//val surfaceContainerDark = Color(0xFF221E24)
//val surfaceContainerHighDark = Color(0xFF2C292E)
//val surfaceContainerHighestDark = Color(0xFF373339)
//
//val primaryDarkMediumContrast = Color(0xFFE0BDFD)
//val onPrimaryDarkMediumContrast = Color(0xFF23063C)
//val primaryContainerDarkMediumContrast = Color(0xFFA484BF)
//val onPrimaryContainerDarkMediumContrast = Color(0xFF000000)
//val secondaryDarkMediumContrast = Color(0xFFD5C5DE)
//val onSecondaryDarkMediumContrast = Color(0xFF1C1224)
//val secondaryContainerDarkMediumContrast = Color(0xFF9A8CA2)
//val onSecondaryContainerDarkMediumContrast = Color(0xFF000000)
//val tertiaryDarkMediumContrast = Color(0xFFF8BBC1)
//val onTertiaryDarkMediumContrast = Color(0xFF2C0B11)
//val tertiaryContainerDarkMediumContrast = Color(0xFFB88388)
//val onTertiaryContainerDarkMediumContrast = Color(0xFF000000)
//val errorDarkMediumContrast = Color(0xFFFFBAB1)
//val onErrorDarkMediumContrast = Color(0xFF370001)
//val errorContainerDarkMediumContrast = Color(0xFFFF5449)
//val onErrorContainerDarkMediumContrast = Color(0xFF000000)
//val backgroundDarkMediumContrast = Color(0xFF151217)
//val onBackgroundDarkMediumContrast = Color(0xFFE8E0E8)
//val surfaceDarkMediumContrast = Color(0xFF151217)
//val onSurfaceDarkMediumContrast = Color(0xFFFFF9FB)
//val surfaceVariantDarkMediumContrast = Color(0xFF4B454D)
//val onSurfaceVariantDarkMediumContrast = Color(0xFFD1C8D3)
//val outlineDarkMediumContrast = Color(0xFFA8A0AA)
//val outlineVariantDarkMediumContrast = Color(0xFF88818A)
//val scrimDarkMediumContrast = Color(0xFF000000)
//val inverseSurfaceDarkMediumContrast = Color(0xFFE8E0E8)
//val inverseOnSurfaceDarkMediumContrast = Color(0xFF2C292E)
//val inversePrimaryDarkMediumContrast = Color(0xFF583C71)
//val surfaceDimDarkMediumContrast = Color(0xFF151217)
//val surfaceBrightDarkMediumContrast = Color(0xFF3C383E)
//val surfaceContainerLowestDarkMediumContrast = Color(0xFF100D12)
//val surfaceContainerLowDarkMediumContrast = Color(0xFF1E1A20)
//val surfaceContainerDarkMediumContrast = Color(0xFF221E24)
//val surfaceContainerHighDarkMediumContrast = Color(0xFF2C292E)
//val surfaceContainerHighestDarkMediumContrast = Color(0xFF373339)
//
//val primaryDarkHighContrast = Color(0xFFFFF9FB)
//val onPrimaryDarkHighContrast = Color(0xFF000000)
//val primaryContainerDarkHighContrast = Color(0xFFE0BDFD)
//val onPrimaryContainerDarkHighContrast = Color(0xFF000000)
//val secondaryDarkHighContrast = Color(0xFFFFF9FB)
//val onSecondaryDarkHighContrast = Color(0xFF000000)
//val secondaryContainerDarkHighContrast = Color(0xFFD5C5DE)
//val onSecondaryContainerDarkHighContrast = Color(0xFF000000)
//val tertiaryDarkHighContrast = Color(0xFFFFF9F9)
//val onTertiaryDarkHighContrast = Color(0xFF000000)
//val tertiaryContainerDarkHighContrast = Color(0xFFF8BBC1)
//val onTertiaryContainerDarkHighContrast = Color(0xFF000000)
//val errorDarkHighContrast = Color(0xFFFFF9F9)
//val onErrorDarkHighContrast = Color(0xFF000000)
//val errorContainerDarkHighContrast = Color(0xFFFFBAB1)
//val onErrorContainerDarkHighContrast = Color(0xFF000000)
//val backgroundDarkHighContrast = Color(0xFF151217)
//val onBackgroundDarkHighContrast = Color(0xFFE8E0E8)
//val surfaceDarkHighContrast = Color(0xFF151217)
//val onSurfaceDarkHighContrast = Color(0xFFFFFFFF)
//val surfaceVariantDarkHighContrast = Color(0xFF4B454D)
//val onSurfaceVariantDarkHighContrast = Color(0xFFFFF9FB)
//val outlineDarkHighContrast = Color(0xFFD1C8D3)
//val outlineVariantDarkHighContrast = Color(0xFFD1C8D3)
//val scrimDarkHighContrast = Color(0xFF000000)
//val inverseSurfaceDarkHighContrast = Color(0xFFE8E0E8)
//val inverseOnSurfaceDarkHighContrast = Color(0xFF000000)
//val inversePrimaryDarkHighContrast = Color(0xFF381D51)
//val surfaceDimDarkHighContrast = Color(0xFF151217)
//val surfaceBrightDarkHighContrast = Color(0xFF3C383E)
//val surfaceContainerLowestDarkHighContrast = Color(0xFF100D12)
//val surfaceContainerLowDarkHighContrast = Color(0xFF1E1A20)
//val surfaceContainerDarkHighContrast = Color(0xFF221E24)
//val surfaceContainerHighDarkHighContrast = Color(0xFF2C292E)
//val surfaceContainerHighestDarkHighContrast = Color(0xFF373339)
//
//val customColorLight = Color(0xFF36693E)
//val onCustomColorLight = Color(0xFFFFFFFF)
//val customColorContainerLight = Color(0xFFB8F1B9)
//val onCustomColorContainerLight = Color(0xFF002108)
//
//val customColorLightMediumContrast = Color(0xFF194C24)
//val onCustomColorLightMediumContrast = Color(0xFFFFFFFF)
//val customColorContainerLightMediumContrast = Color(0xFF4C8052)
//val onCustomColorContainerLightMediumContrast = Color(0xFFFFFFFF)
//
//val customColorLightHighContrast = Color(0xFF00290C)
//val onCustomColorLightHighContrast = Color(0xFFFFFFFF)
//val customColorContainerLightHighContrast = Color(0xFF194C24)
//val onCustomColorContainerLightHighContrast = Color(0xFFFFFFFF)
//
//val customColorDark = Color(0xFF9CD49F)
//val onCustomColorDark = Color(0xFF003913)
//val customColorContainerDark = Color(0xFF1D5128)
//val onCustomColorContainerDark = Color(0xFFB8F1B9)
//
//val customColorDarkMediumContrast = Color(0xFFA0D8A3)
//val onCustomColorDarkMediumContrast = Color(0xFF001B06)
//val customColorContainerDarkMediumContrast = Color(0xFF689D6C)
//val onCustomColorContainerDarkMediumContrast = Color(0xFF000000)
//
//val customColorDarkHighContrast = Color(0xFFF0FFEC)
//val onCustomColorDarkHighContrast = Color(0xFF000000)
//val customColorContainerDarkHighContrast = Color(0xFFA0D8A3)
//val onCustomColorContainerDarkHighContrast = Color(0xFF000000)
//
//@Immutable
//data class ExtendedColorScheme(
//    val customColor: ColorFamily,
//)
//
//private val lightScheme = lightColorScheme(
//    primary = primaryLight,
//    onPrimary = onPrimaryLight,
//    primaryContainer = primaryContainerLight,
//    onPrimaryContainer = onPrimaryContainerLight,
//    secondary = secondaryLight,
//    onSecondary = onSecondaryLight,
//    secondaryContainer = secondaryContainerLight,
//    onSecondaryContainer = onSecondaryContainerLight,
//    tertiary = tertiaryLight,
//    onTertiary = onTertiaryLight,
//    tertiaryContainer = tertiaryContainerLight,
//    onTertiaryContainer = onTertiaryContainerLight,
//    error = errorLight,
//    onError = onErrorLight,
//    errorContainer = errorContainerLight,
//    onErrorContainer = onErrorContainerLight,
//    background = backgroundLight,
//    onBackground = onBackgroundLight,
//    surface = surfaceLight,
//    onSurface = onSurfaceLight,
//    surfaceVariant = surfaceVariantLight,
//    onSurfaceVariant = onSurfaceVariantLight,
//    outline = outlineLight,
//    outlineVariant = outlineVariantLight,
//    scrim = scrimLight,
//    inverseSurface = inverseSurfaceLight,
//    inverseOnSurface = inverseOnSurfaceLight,
//    inversePrimary = inversePrimaryLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
//)
//
//private val darkScheme = darkColorScheme(
//    primary = primaryDark,
//    onPrimary = onPrimaryDark,
//    primaryContainer = primaryContainerDark,
//    onPrimaryContainer = onPrimaryContainerDark,
//    secondary = secondaryDark,
//    onSecondary = onSecondaryDark,
//    secondaryContainer = secondaryContainerDark,
//    onSecondaryContainer = onSecondaryContainerDark,
//    tertiary = tertiaryDark,
//    onTertiary = onTertiaryDark,
//    tertiaryContainer = tertiaryContainerDark,
//    onTertiaryContainer = onTertiaryContainerDark,
//    error = errorDark,
//    onError = onErrorDark,
//    errorContainer = errorContainerDark,
//    onErrorContainer = onErrorContainerDark,
//    background = backgroundDark,
//    onBackground = onBackgroundDark,
//    surface = surfaceDark,
//    onSurface = onSurfaceDark,
//    surfaceVariant = surfaceVariantDark,
//    onSurfaceVariant = onSurfaceVariantDark,
//    outline = outlineDark,
//    outlineVariant = outlineVariantDark,
//    scrim = scrimDark,
//    inverseSurface = inverseSurfaceDark,
//    inverseOnSurface = inverseOnSurfaceDark,
//    inversePrimary = inversePrimaryDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
//)
//
//private val mediumContrastLightColorScheme = lightColorScheme(
//    primary = primaryLightMediumContrast,
//    onPrimary = onPrimaryLightMediumContrast,
//    primaryContainer = primaryContainerLightMediumContrast,
//    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
//    secondary = secondaryLightMediumContrast,
//    onSecondary = onSecondaryLightMediumContrast,
//    secondaryContainer = secondaryContainerLightMediumContrast,
//    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
//    tertiary = tertiaryLightMediumContrast,
//    onTertiary = onTertiaryLightMediumContrast,
//    tertiaryContainer = tertiaryContainerLightMediumContrast,
//    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
//    error = errorLightMediumContrast,
//    onError = onErrorLightMediumContrast,
//    errorContainer = errorContainerLightMediumContrast,
//    onErrorContainer = onErrorContainerLightMediumContrast,
//    background = backgroundLightMediumContrast,
//    onBackground = onBackgroundLightMediumContrast,
//    surface = surfaceLightMediumContrast,
//    onSurface = onSurfaceLightMediumContrast,
//    surfaceVariant = surfaceVariantLightMediumContrast,
//    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
//    outline = outlineLightMediumContrast,
//    outlineVariant = outlineVariantLightMediumContrast,
//    scrim = scrimLightMediumContrast,
//    inverseSurface = inverseSurfaceLightMediumContrast,
//    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
//    inversePrimary = inversePrimaryLightMediumContrast,
//    surfaceDim = surfaceDimLightMediumContrast,
//    surfaceBright = surfaceBrightLightMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
//    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
//    surfaceContainer = surfaceContainerLightMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
//)
//
//private val highContrastLightColorScheme = lightColorScheme(
//    primary = primaryLightHighContrast,
//    onPrimary = onPrimaryLightHighContrast,
//    primaryContainer = primaryContainerLightHighContrast,
//    onPrimaryContainer = onPrimaryContainerLightHighContrast,
//    secondary = secondaryLightHighContrast,
//    onSecondary = onSecondaryLightHighContrast,
//    secondaryContainer = secondaryContainerLightHighContrast,
//    onSecondaryContainer = onSecondaryContainerLightHighContrast,
//    tertiary = tertiaryLightHighContrast,
//    onTertiary = onTertiaryLightHighContrast,
//    tertiaryContainer = tertiaryContainerLightHighContrast,
//    onTertiaryContainer = onTertiaryContainerLightHighContrast,
//    error = errorLightHighContrast,
//    onError = onErrorLightHighContrast,
//    errorContainer = errorContainerLightHighContrast,
//    onErrorContainer = onErrorContainerLightHighContrast,
//    background = backgroundLightHighContrast,
//    onBackground = onBackgroundLightHighContrast,
//    surface = surfaceLightHighContrast,
//    onSurface = onSurfaceLightHighContrast,
//    surfaceVariant = surfaceVariantLightHighContrast,
//    onSurfaceVariant = onSurfaceVariantLightHighContrast,
//    outline = outlineLightHighContrast,
//    outlineVariant = outlineVariantLightHighContrast,
//    scrim = scrimLightHighContrast,
//    inverseSurface = inverseSurfaceLightHighContrast,
//    inverseOnSurface = inverseOnSurfaceLightHighContrast,
//    inversePrimary = inversePrimaryLightHighContrast,
//    surfaceDim = surfaceDimLightHighContrast,
//    surfaceBright = surfaceBrightLightHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
//    surfaceContainerLow = surfaceContainerLowLightHighContrast,
//    surfaceContainer = surfaceContainerLightHighContrast,
//    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
//)
//
//private val mediumContrastDarkColorScheme = darkColorScheme(
//    primary = primaryDarkMediumContrast,
//    onPrimary = onPrimaryDarkMediumContrast,
//    primaryContainer = primaryContainerDarkMediumContrast,
//    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
//    secondary = secondaryDarkMediumContrast,
//    onSecondary = onSecondaryDarkMediumContrast,
//    secondaryContainer = secondaryContainerDarkMediumContrast,
//    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
//    tertiary = tertiaryDarkMediumContrast,
//    onTertiary = onTertiaryDarkMediumContrast,
//    tertiaryContainer = tertiaryContainerDarkMediumContrast,
//    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
//    error = errorDarkMediumContrast,
//    onError = onErrorDarkMediumContrast,
//    errorContainer = errorContainerDarkMediumContrast,
//    onErrorContainer = onErrorContainerDarkMediumContrast,
//    background = backgroundDarkMediumContrast,
//    onBackground = onBackgroundDarkMediumContrast,
//    surface = surfaceDarkMediumContrast,
//    onSurface = onSurfaceDarkMediumContrast,
//    surfaceVariant = surfaceVariantDarkMediumContrast,
//    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
//    outline = outlineDarkMediumContrast,
//    outlineVariant = outlineVariantDarkMediumContrast,
//    scrim = scrimDarkMediumContrast,
//    inverseSurface = inverseSurfaceDarkMediumContrast,
//    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
//    inversePrimary = inversePrimaryDarkMediumContrast,
//    surfaceDim = surfaceDimDarkMediumContrast,
//    surfaceBright = surfaceBrightDarkMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
//    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
//    surfaceContainer = surfaceContainerDarkMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
//)
//
//private val highContrastDarkColorScheme = darkColorScheme(
//    primary = primaryDarkHighContrast,
//    onPrimary = onPrimaryDarkHighContrast,
//    primaryContainer = primaryContainerDarkHighContrast,
//    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
//    secondary = secondaryDarkHighContrast,
//    onSecondary = onSecondaryDarkHighContrast,
//    secondaryContainer = secondaryContainerDarkHighContrast,
//    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
//    tertiary = tertiaryDarkHighContrast,
//    onTertiary = onTertiaryDarkHighContrast,
//    tertiaryContainer = tertiaryContainerDarkHighContrast,
//    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
//    error = errorDarkHighContrast,
//    onError = onErrorDarkHighContrast,
//    errorContainer = errorContainerDarkHighContrast,
//    onErrorContainer = onErrorContainerDarkHighContrast,
//    background = backgroundDarkHighContrast,
//    onBackground = onBackgroundDarkHighContrast,
//    surface = surfaceDarkHighContrast,
//    onSurface = onSurfaceDarkHighContrast,
//    surfaceVariant = surfaceVariantDarkHighContrast,
//    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
//    outline = outlineDarkHighContrast,
//    outlineVariant = outlineVariantDarkHighContrast,
//    scrim = scrimDarkHighContrast,
//    inverseSurface = inverseSurfaceDarkHighContrast,
//    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
//    inversePrimary = inversePrimaryDarkHighContrast,
//    surfaceDim = surfaceDimDarkHighContrast,
//    surfaceBright = surfaceBrightDarkHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
//    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
//    surfaceContainer = surfaceContainerDarkHighContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
//)
//
//val extendedLight = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorLight,
//        onCustomColorLight,
//        customColorContainerLight,
//        onCustomColorContainerLight,
//    ),
//)
//
//val extendedDark = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorDark,
//        onCustomColorDark,
//        customColorContainerDark,
//        onCustomColorContainerDark,
//    ),
//)
//
//val extendedLightMediumContrast = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorLightMediumContrast,
//        onCustomColorLightMediumContrast,
//        customColorContainerLightMediumContrast,
//        onCustomColorContainerLightMediumContrast,
//    ),
//)
//
//val extendedLightHighContrast = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorLightHighContrast,
//        onCustomColorLightHighContrast,
//        customColorContainerLightHighContrast,
//        onCustomColorContainerLightHighContrast,
//    ),
//)
//
//val extendedDarkMediumContrast = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorDarkMediumContrast,
//        onCustomColorDarkMediumContrast,
//        customColorContainerDarkMediumContrast,
//        onCustomColorContainerDarkMediumContrast,
//    ),
//)
//
//val extendedDarkHighContrast = ExtendedColorScheme(
//    customColor = ColorFamily(
//        customColorDarkHighContrast,
//        onCustomColorDarkHighContrast,
//        customColorContainerDarkHighContrast,
//        onCustomColorContainerDarkHighContrast,
//    ),
//)
//
//@Immutable
//data class ColorFamily(
//    val color: Color,
//    val onColor: Color,
//    val colorContainer: Color,
//    val onColorContainer: Color
//)
//
//val unspecified_scheme = ColorFamily(
//    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
//)
//
//@Composable
//fun AppTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable() () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> darkScheme
//        else -> lightScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}

