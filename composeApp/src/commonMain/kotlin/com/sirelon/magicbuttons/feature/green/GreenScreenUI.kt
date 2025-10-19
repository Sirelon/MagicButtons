package com.sirelon.magicbuttons.feature.green

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GreenScreenUI() {
    val viewModel = koinViewModel<GreenViewModel>()

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()


}