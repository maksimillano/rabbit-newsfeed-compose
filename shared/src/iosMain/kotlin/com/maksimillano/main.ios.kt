package com.maksimillano

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun ChatViewController(): UIViewController = ComposeUIViewController {
    AppThemeScreen()
}