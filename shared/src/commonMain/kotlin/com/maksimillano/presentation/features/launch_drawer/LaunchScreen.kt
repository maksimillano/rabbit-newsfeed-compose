package com.maksimillano.presentation.features.launch_drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.maksimillano.MR
import com.maksimillano.presentation.component.colorThemed
import com.maksimillano.presentation.features.newsfeed.NewsFeedComponent
import com.maksimillano.util.stringValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LaunchScreen(launchComponent: LaunchComponentImpl) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val closeAction: () -> Unit = remember {
        {
            scope.launch {
                drawerState.close()
            }
        }
    }
    launchComponent.navigationComponent.closeDrawerAction = closeAction

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            launchComponent.navigationComponent.render()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AppBarWithContent(drawerState, launchComponent.newsFeedComponent, scope)
            }
        }
    )
}

@Composable
private fun AppBarWithContent(state: DrawerState, newsFeedComponent: NewsFeedComponent, scope: CoroutineScope) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringValue(MR.strings.rabbit_app_name),
                        color = colorThemed { it.textColor },
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
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Navigation Drawer",
                            tint = colorThemed { it.primaryContentColor }
                        )
                    }
                },
                backgroundColor = colorThemed { it.primaryColor },
            )
        },
        content = {
            newsFeedComponent.render()
        },
        backgroundColor = colorThemed { it.backgroundBarColor }
    )
}