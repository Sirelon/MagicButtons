package com.sirelon.magicbuttons.feature.green

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sirelon.magicbuttons.designsystem.buttons.MagicBlueButton
import com.sirelon.magicbuttons.designsystem.buttons.MagicGreenButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GreenScreen(
    openBlueScreen: (counter: Int) -> Unit,
) {
    val viewModel = koinViewModel<GreenViewModel>()

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    val effectState = viewModel.effectFlow.collectAsStateWithLifecycle(null)
    val effect = effectState.value
    LaunchedEffect(effect) {
        when (effect) {
            is GreenContract.Effect.GoToBlue -> openBlueScreen(effect.counter)
            null -> {
                // no op
            }
        }
    }

    GreenScreenUI(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GreenScreenUI(
    state: GreenContract.State,
    onEvent: (GreenContract.Event) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Green Button Screen") },
            )
        },
        bottomBar = {
            MagicBlueButton(
                modifier = Modifier
                    .background(Color(0xFF43A980))
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                text = "Open Blue",
                onClick = { onEvent(GreenContract.Event.GoToBlueClicked) },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(
                space = 32.dp,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MagicGreenButton(
                modifier = Modifier.size(
                    width = 224.dp,
                    height = 236.dp,
                ),
                text = state.buttonText,
                onClick = { onEvent(GreenContract.Event.ButtonClicked) },
            )

            Text(text = "Counter: ${state.counter}")
        }
    }
}