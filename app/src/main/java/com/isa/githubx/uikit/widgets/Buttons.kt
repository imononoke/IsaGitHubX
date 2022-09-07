package com.isa.githubx.uikit.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isa.githubx.uikit.theme.MaterialColors
import com.isa.githubx.R

object CommonButtonDefaults {

    @Suppress("MemberVisibilityCanBePrivate")
    val MinHeightLarge = 48.dp

    @Suppress("unused")
    val MinHeightMedium = 40.dp

    @Suppress("unused")
    val MinHeightSmall = 32.dp

    val MinHeightDefault = MinHeightLarge

    const val CornerSizePercent = 50

    const val DisableEffectAlpha: Float = 0.75f
}

@Composable
fun CommonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = CommonButtonDefaults.CornerSizePercent)),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialColors.primary,
        disabledBackgroundColor = MaterialColors.onPrimary.copy(CommonButtonDefaults.DisableEffectAlpha)
            .compositeOver(MaterialColors.primary),
        disabledContentColor = MaterialColors.onPrimary
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = CommonButtonDefaults.MinHeightDefault),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content,
    )
}

@Preview
@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    hint: Int = R.string.search_field_hint,
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = modifier.clickable(onClick = onClick),
        enabled = false,
        placeholder = {
            Text(text = stringResource(id = hint))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        }
    )
}
