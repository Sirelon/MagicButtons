package com.sirelon.magicbuttons

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sirelon.magicbuttons.designsystem.AppTheme
import com.sirelon.magicbuttons.feature.blue.BlueScreenUI
import com.sirelon.magicbuttons.feature.green.GreenScreenUI
import com.sirelon.magicbuttons.feature.green.di.greenModule
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinApplication

@Serializable
private sealed interface Route {

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
        AppTheme {
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
                    BlueScreenUI(onBack = navController::popBackStack)
                }
            }
        }
    }
}