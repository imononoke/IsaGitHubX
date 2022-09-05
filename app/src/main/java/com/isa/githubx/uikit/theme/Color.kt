package com.isa.githubx.uikit.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    accent: Color,
    accentDark: Color,
    accentLight: Color,
    accentLighten: Color,
    neutral: Color,
    neutralDarkest: Color,
    neutralDarken: Color,
    neutralDark: Color,
    neutralLight: Color,
    neutralLighten: Color,
    neutralLightest: Color,
    warning: Color,
    info: Color,
    success: Color,
    isLight: Boolean,
) {
    var accent by mutableStateOf(accent)
        private set
    var accentDark by mutableStateOf(accentDark)
        private set
    var accentLight by mutableStateOf(accentLight)
        private set
    var accentLighten by mutableStateOf(accentLighten)
        private set

    var neutral by mutableStateOf(neutral)
        private set
    var neutralDarkest by mutableStateOf(neutralDarkest)
        private set
    var neutralDarken by mutableStateOf(neutralDarken)
        private set
    var neutralDark by mutableStateOf(neutralDark)
        private set
    var neutralLight by mutableStateOf(neutralLight)
        private set
    var neutralLighten by mutableStateOf(neutralLighten)
        private set
    var neutralLightest by mutableStateOf(neutralLightest)
        private set

    var warning by mutableStateOf(warning)
        private set
    var info by mutableStateOf(info)
        private set
    var success by mutableStateOf(success)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun update(other: AppColors) {
        accent = other.accent
        accentDark = other.accentDark
        accentLight = other.accentLight
        accentLighten = other.accentLighten

        neutral = other.neutral
        neutralDarkest = other.neutralDarkest
        neutralDarken = other.neutralDarken
        neutralDark = other.neutralDark
        neutralLight = other.neutralLight
        neutralLighten = other.neutralLighten
        neutralLightest = other.neutralLightest

        warning = other.warning
        info = other.info
        success = other.success
        isLight = other.isLight
    }
}

internal val LightColorPalette = lightColors(
    primary = AppColor.primary,
    primaryVariant = AppColor.primaryLight,
    secondary = AppColor.secondary,
    secondaryVariant = AppColor.secondaryLight,
    background = AppColor.neutralLightest,
    surface = AppColor.neutralLightest,
    onPrimary = AppColor.neutralLightest,
    onSecondary = AppColor.neutralLightest,
    onBackground = AppColor.neutralDark,
    onSurface = AppColor.neutralDark,
    error = AppColor.error,
    onError = AppColor.neutralLightest
)

internal val DarkColorPalette = darkColors(
    primary = AppColor.primary,
    primaryVariant = AppColor.primaryDark,
    secondary = AppColor.secondary,
    secondaryVariant = AppColor.secondaryDark,
    background = AppColor.neutralDarkest,
    surface = AppColor.neutralDarkest,
    onPrimary = AppColor.neutralLightest,
    onSecondary = AppColor.neutralLightest,
    onBackground = AppColor.neutralLight,
    onSurface = AppColor.neutralLight,
    error = AppColor.error,
    onError = AppColor.neutralLightest
)

internal val LightExtendedColorPalette = AppColors(
    accent = AppColor.accent,
    accentDark = AppColor.accentDark,
    accentLight = AppColor.accentLight,
    accentLighten = AppColor.accentLighten,
    neutral = AppColor.neutral,
    neutralDarkest = AppColor.neutralDarkest,
    neutralDarken = AppColor.neutralDarken,
    neutralDark = AppColor.neutralDark,
    neutralLight = AppColor.neutralLight,
    neutralLighten = AppColor.neutralLighten,
    neutralLightest = AppColor.neutralLightest,
    warning = AppColor.warning,
    info = AppColor.info,
    success = AppColor.success,
    isLight = true
)

private object AppColor {
    val primary = Color(0xFF364F6B)
    val primaryDark = Color(0xFF364F6B)
    val primaryLight = Color(0xFF3FC1C9)

    val secondary = Color(0xFFDB2C7B)
    val secondaryDark = Color(0xFFAF346B)
    val secondaryLight = Color(0xFFF56AA8)

    val accent = Color(0xFF364F6B)
    val accentDark = Color(0xFF364F6B)
    val accentLight = Color(0xFF3FC1C9)
    val accentLighten = Color(0xFF3FC1C9)

    // for text, dividers, backgrounds and other UI elements
    val neutral = Color(0xFF677383)
    val neutralDarkest = Color(0xFF000000)
    val neutralDarken = Color(0xFF191C1F)
    val neutralDark = Color(0xFF2F343B)
    val neutralLight = Color(0xFFD1DCE5)
    val neutralLighten = Color(0xFFF6F8F9)
    val neutralLightest = Color(0xFFFFFFFF)

    val error = Color(0xFFCC371F)
    val warning = Color(0xFFF2B824)
    val info = Color(0xFF1475C4)
    val success = Color(0xFF00B44C)
}
