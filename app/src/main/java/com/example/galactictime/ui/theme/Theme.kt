package com.example.galactictime.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = Color.White,
    tertiary = Teal200,
    onPrimary = Color.Black,
    background = Color.Black

    // error, primaryContainer, onSecondary, etc.
)
private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color.Cyan,
    tertiary = Color.Green,
    background = Color.Black
    // error, primaryContainer, onSecondary, etc.
)


/*
private val DarkColorPalette = darkColors(
        primary = Color.White,
        primaryVariant = Color.Black,
        secondary = Color.White
)


private val LightColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
*/

@SuppressLint("NewApi")
@Composable
fun GalacticTimeTheme(isDarkTheme: Boolean = isSystemInDarkTheme(),
                      isDynamicColor: Boolean = true,
                      content: @Composable () -> Unit)
{
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val myColorScheme = when {
        dynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        dynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = myColorScheme
    ) {
        // TODO (M3): MaterialTheme doesn't provide LocalIndication, remove when it does
        val rippleIndication = rememberRipple()
        CompositionLocalProvider(
            LocalIndication provides rippleIndication,
            content = content
        )
    }
}