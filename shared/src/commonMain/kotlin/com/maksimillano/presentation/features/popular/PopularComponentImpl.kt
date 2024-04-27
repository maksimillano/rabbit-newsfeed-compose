package com.maksimillano.presentation.features.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.maksimillano.presentation.base.RenderComponent
import com.maksimillano.presentation.base.AppNavigator

class PopularComponentImpl(
    componentContext: ComponentContext,
    override val appRouter: AppNavigator
) : PopularComponent,
    RenderComponent,
    ComponentContext by componentContext {
    override val viewModel: PopularViewModel
        get() = TODO("Not yet implemented")

    @Composable
    override fun render() {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xff261f64))
        ) {
            Text("${PopularComponentImpl::class.simpleName}", modifier = Modifier.align(Alignment.Center))
        }
    }
}