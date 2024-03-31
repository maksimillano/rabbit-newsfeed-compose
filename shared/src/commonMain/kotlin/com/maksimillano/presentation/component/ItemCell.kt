package com.maksimillano.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maksimillano.api.theme.ColorScheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ItemCell(
    iconRes: String,
    title: String,
    colorTint: (ColorScheme) -> Color = { it.accentColor },
    clickAction: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                clickAction()
            }
    ) {
        val painter = painterResource(iconRes)
        Image(
            painter = painter,
            contentDescription = iconRes.substringBeforeLast(".", "Menu Item"),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 18.dp)
                .size(20.dp),
            colorFilter = ColorFilter.tint(
                colorThemed { colorTint(it) }
            )
        )

        Text(
            text = title,
            color = colorThemed { colorTint(it) },
            fontSize = dimenThemed { it.textMedium },
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}