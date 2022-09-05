package com.isa.githubx.uikit.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.isa.githubx.R
import com.isa.githubx.uikit.Icons

@Composable
internal fun Header(
    headerType: HeaderType,
    onCloseButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (headerType) {
        HeaderType.CLOSE -> {
            IconButton(
                onClick = onCloseButtonClick,
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(Icons.Close),
                    contentDescription = stringResource(R.string.login_close),
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colors.background
                )
            }
        }

        HeaderType.BLANK -> {
            Box(modifier)
        }
    }
}

internal enum class HeaderType {
    CLOSE, BLANK
}
