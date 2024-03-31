package com.maksimillano.presentation.features.launch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.maksimillano.presentation.component.DrawerNavigationPanel
import com.maksimillano.presentation.component.colorThemed
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponent
import com.maksimillano.presentation.features.newsfeed.NewsFeedScreen

@Composable
fun LaunchScreen(launchComponent: LaunchComponent) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val dataState = launchComponent.viewModel.state.collectAsState()

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerNavigationPanel(
                state = dataState,
                updateTheme = launchComponent::updateTheme,
                logout = launchComponent::logout
            )
        },
        content = {
            // TODO view pager

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AppBarWithContent(drawerState) { launchComponent.newsFeedComponent }
            }
        }
    )
}

@Composable
private fun AppBarWithContent(state: DrawerState, newsFeedComponentProvider: () -> NewsFeedComponent) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rabbit",
                        color = colorThemed { it.primaryContentColor },
                        fontSize = 24.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                state.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            "Navigation Drawer",
                            tint = colorThemed { it.primaryContentColor }
                        )
                    }
                },
                backgroundColor = colorThemed { it.primaryColor },
            )
        },
        content = {
            NewsFeedScreen(newsFeedComponentProvider())
        },
        backgroundColor = colorThemed { it.backgroundColor },
    )
}