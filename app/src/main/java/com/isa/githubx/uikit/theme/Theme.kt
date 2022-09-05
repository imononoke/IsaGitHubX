package com.isa.githubx.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.isa.githubx.uikit.theme.*
import com.isa.githubx.uikit.theme.DarkColorPalette
import com.isa.githubx.uikit.theme.LightColorPalette
import com.isa.githubx.uikit.theme.LightExtendedColorPalette
import com.isa.githubx.uikit.theme.Typography

val MaterialColors: Colors
    @Composable get() = MaterialTheme.colors

@Composable
fun AppTheme(
    @Suppress("UNUSED_PARAMETER") darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val extendedColors = LightExtendedColorPalette
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideExtendedColors(extendedColors) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Suppress("unused")
val MaterialTheme.extendedColors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current

@Composable
fun ProvideExtendedColors(
    colors: AppColors,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalExtendedColors provides colorPalette, content = content)
}

private val LocalExtendedColors = staticCompositionLocalOf<AppColors> {
    error("No ExtendedColorPalette provided")
}
