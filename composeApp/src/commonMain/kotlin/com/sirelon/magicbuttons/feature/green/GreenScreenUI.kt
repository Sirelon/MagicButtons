package com.sirelon.magicbuttons.feature.green

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sirelon.magicbuttons.designsystem.buttons.MagicGreenButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GreenScreenUI(
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

    GreenScreen(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GreenScreen(
    state: GreenContract.State,
    onEvent: (GreenContract.Event) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Green Button Screen") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            MagicGreenButton(
                text = state.buttonText,
                onClick = { onEvent(GreenContract.Event.ButtonClicked) },
            )
        }
    }
}