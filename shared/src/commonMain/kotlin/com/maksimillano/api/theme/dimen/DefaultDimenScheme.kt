package com.maksimillano.api.theme.dimen

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.maksimillano.api.theme.DimenScheme

class DefaultDimenScheme : DimenScheme {
    override val postTitle: TextUnit = 14.sp
    override val hint: TextUnit = 13.sp
    override val postContent: TextUnit = 15.sp

    override val textSmall = 14.sp
    override val textMedium = 18.sp
    override val textLarge = 22.sp
}