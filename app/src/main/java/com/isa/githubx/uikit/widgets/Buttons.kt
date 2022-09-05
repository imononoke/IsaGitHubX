package com.isa.githubx.uikit.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.isa.githubx.uikit.theme.Dimens
import com.isa.githubx.uikit.theme.MaterialColors
import com.isa.githubx.uikit.theme.Shapes

@Composable
fun commonButton(
    text: String,
    modifier: Modifier = Modifier,
    bgColor: Color = MaterialColors.onPrimary,
    textColor: Color = MaterialColors.primary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeight)
            .background(color = bgColor, shape = Shapes.small)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text, color = textColor, modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    commonButton(
        text = text,
        modifier = modifier,
        onClick = onClick
    )
}
