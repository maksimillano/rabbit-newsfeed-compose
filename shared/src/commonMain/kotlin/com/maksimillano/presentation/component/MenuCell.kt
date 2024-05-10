package com.maksimillano.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maksimillano.util.stringValue
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.desc.image.asImageDesc

@Composable
fun MenuCell(
    modifier: Modifier = Modifier,
    iconRes: ImageResource,
    titleRes: StringResource,
    clickAction: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                clickAction()
            }
    ) {
        AppImage(
            resource = iconRes,
            contentDescription = iconRes.asImageDesc().toString(),
            modifier = Modifier
                .align(Alignment.CenterVertically),
            colorFilter = ColorFilter.tint(
                MaterialTheme.colorScheme.onBackground
            )
        )

        Text(
            text = stringValue(titleRes),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        )
    }
}