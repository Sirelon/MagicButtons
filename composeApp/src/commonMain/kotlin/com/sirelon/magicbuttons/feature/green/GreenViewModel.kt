package com.sirelon.magicbuttons.feature.green

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal interface GreenContract {

    data class State(
        val buttonText: String,
        val buttonVisible: Boolean,
    )

    sealed interface Event {
        object ButtonClicked : Event
    }

    sealed interface Effect {
        data class GoToBlue(val counter: Int) : Effect
    }
}

internal class GreenViewModel : ViewModel() {

    private val stateEmitter = MutableStateFlow(initialState())

    val stateFlow: StateFlow<GreenContract.State> = stateEmitter.asStateFlow()

    private val effectEmitter = Channel<GreenContract.Effect>()
    val effectFlow: Flow<GreenContract.Effect> = effectEmitter.receiveAsFlow()

    init {
        viewModelScope.launch {
            stateEmitter.emit(GreenContract.State(buttonText = "Старт", buttonVisible = true))
        }
    }

    private fun initialState(): GreenContract.State =
        GreenContract.State(buttonText = "", buttonVisible = false)

    fun onEvent(event: GreenContract.Event) {
        when (event) {
            GreenContract.Event.ButtonClicked -> {
                setEffect { GreenContract.Effect.GoToBlue(counter = 10) }
            }
        }
    }

    private fun setEffect(effect: () -> GreenContract.Effect) {

        viewModelScope.launch {
            effectEmitter.send(effect())
        }
    }
}