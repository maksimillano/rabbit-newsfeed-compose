package com.maksimillano.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.maksimillano.presentation.features.launch.LaunchState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun DrawerNavigationPanel(
    state: State<LaunchState>,
    updateTheme: () -> Unit,
    logout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .backgroundThemed { it.backgroundColor }
    ) {
        DrawerAppBar(state, updateTheme)
        DrawerItemsList(logout)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DrawerAppBar(
    state: State<LaunchState>,
    updateTheme: () -> Unit
) {
//    val launchState = state.value
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .backgroundThemed { it.primaryColor }
//            .padding(
//                horizontal = 14.dp,
//                vertical = 14.dp
//            )
//    ) {
//        val engine = EngineProvider.get()
//        val currentUser = engine.engineUser
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 14.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
////            asyncPainterResource(
////                currentUser.
////            )
////            KamelImage(
////
////            )
//
//            // TODO: support error result
//            val url = if (launchState is LaunchState.Loaded && launchState.engineUser is AuthedUser) launchState.engineUser.photo else ""
//            val painter = asyncPainterResource(
//                data = url
//            )
//
//            KamelImage(
//                resource = painter,
//                contentDescription = "Profile picture",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape)
//                    .border(
//                        2.dp,
//                        colorThemed { it.borderColor },
//                        CircleShape
//                    )
//                    .align(Alignment.CenterVertically)
//            )
//
//            val nightMode = painterResource(
//                "night-mode.png"
//            )
//            Image(
//                painter = nightMode,
//                contentDescription = "Night mode",
//                modifier = Modifier
//                    .size(35.dp)
//                    .clickableNoRipple { updateTheme() }
//                    .align(Alignment.Top),
//                colorFilter = ColorFilter.tint(
//                    colorThemed { it.accentColor }
//                )
//            )
//        }
//
//        Row(
//           modifier = Modifier
//               .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = if (launchState is LaunchState.Loaded && launchState.engineUser is AuthedUser) "${launchState.engineUser.firstName} ${launchState.engineUser.lastName}" else "",
//                    color = colorThemed { it.textColor },
//                    fontSize = dimenThemed { it.textMedium },
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "+7 (965) 781-53-08",
//                    color = colorThemed { it.subtitleColor },
//                    fontSize = dimenThemed { it.textSmall },
//                    modifier = Modifier
//                )
//            }
//
////            Image(
////                contentDescription = "Show another accounts",
////
////            )
//        }
//    }
}

@Composable
fun DrawerItemsList(logout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 14.dp,
                vertical = 14.dp
            )
    ) {
        ItemCell(
            iconRes = "logout.png",
            title = "Выйти из профиля",
            colorTint = { it.dangerActionColor },
            clickAction = logout
        )
    }
}