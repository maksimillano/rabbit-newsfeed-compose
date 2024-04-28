package com.maksimillano.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maksimillano.MR
import com.maksimillano.api.domain.features.navigation.NavigationMenuItem
import com.maksimillano.presentation.features.navigation.NavigationState
import com.maksimillano.util.clickableNoRipple
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun DrawerNavigationPanel(
    state: NavigationState,
    updateTheme: () -> Unit,
    accountSelect: () -> Unit,
    anotherAccountSelect: (number: Int) -> Unit,
    menuSelect: (NavigationMenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .backgroundThemed { it.backgroundColor }
    ) {
        DrawerAppBar(state, updateTheme, accountSelect, anotherAccountSelect)
        DrawerItemsList(state, menuSelect)
    }
}

@Composable
private fun DrawerAppBar(
    state: NavigationState,
    updateTheme: () -> Unit,
    accountSelect: () -> Unit,
    anotherAccountSelect: (number: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundThemed { it.primaryColor }
            .padding(
                horizontal = 14.dp,
                vertical = 14.dp
            )
    ) {
        when (val accountBadge = state.accountBadge) {
            is NavigationState.AccountInfoBadge.UsersBadge -> {
                ShowUsersBadge(accountBadge, state.theme, updateTheme, accountSelect, anotherAccountSelect)
            }
            is NavigationState.AccountInfoBadge.LoginBadge -> {
                ShowLoginBadge(accountBadge, state.theme)
            }
        }
    }
}

@Composable
fun ColumnScope.ShowUsersBadge(
    accountBadge: NavigationState.AccountInfoBadge.UsersBadge,
    theme: NavigationState.ThemeType,
    updateTheme: () -> Unit,
    accountSelect: () -> Unit,
    anotherAccountSelect: (number: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // TODO: support error result
        val url = accountBadge.current.photo
        val painter = asyncPainterResource(
            data = url
        )

        KamelImage(
            resource = painter,
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(
                    2.dp,
                    colorThemed { it.borderColor },
                    CircleShape
                )
                .align(Alignment.CenterVertically)
                .clickableNoRipple {
                    accountSelect()
                }
        )

        val nightMode = when (theme) {
            NavigationState.ThemeType.LIGHT -> painterResource(MR.images.sun)
            NavigationState.ThemeType.DARK -> painterResource(MR.images.moon_start)
        }
        Image(
            painter = nightMode,
            contentDescription = "Night mode",
            modifier = Modifier
                .size(35.dp)
                .clickableNoRipple { updateTheme() }
                .align(Alignment.Top),
            colorFilter = ColorFilter.tint(
                colorThemed { it.accentColor }
            )
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${accountBadge.current.firstName} ${accountBadge.current.lastName}",
                color = colorThemed { it.textColor },
                fontSize = dimenThemed { it.textMedium },
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Text(
                text = if (accountBadge.current.phone.isEmpty()) "+7 (965) 781-53-08" else accountBadge.current.phone,
                color = colorThemed { it.subtitleColor },
                fontSize = dimenThemed { it.textSmall },
                modifier = Modifier
            )
        }
    }
}


@Composable
fun ColumnScope.ShowLoginBadge(
    accountBadge: NavigationState.AccountInfoBadge.LoginBadge,
    theme: NavigationState.ThemeType,
) {
    // TODO
}

@Composable
private fun DrawerItemsList(state: NavigationState, menuSelect: (NavigationMenuItem) -> Unit) {
    val list = state.menuItems
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(list) {
            MenuCell(
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                iconRes = MR.images.leave,
                titleRes = MR.strings.menu_logout,
                clickAction = {
                    menuSelect(it)
                }
            )
        }
    }
}