package com.sirelon.magicbuttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sirelon.magicbuttons.designsystem.appColors
import com.sirelon.magicbuttons.feature.green.GreenScreenUI
import com.sirelon.magicbuttons.feature.green.di.greenModule
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import magicbuttons.composeapp.generated.resources.Res
import magicbuttons.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.KoinApplication

@Serializable
private sealed interface Route {

    @Serializable
    data object Start : Route

    @Serializable
    data object Green : Route

    @Serializable
    data class Blue(val counter: Int) : Route
}

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(greenModule)
        },
    ) {
        MaterialTheme(
            colorScheme = appColors(),
        ) {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Route.Green,
            ) {
                composable<Route.Green> {
                    GreenScreenUI(
                        openBlueScreen = {
                            navController.navigate(Route.Blue(counter = it))
                        },
                    )
                }

                composable<Route.Blue> {

                }
            }
        }
    }
}