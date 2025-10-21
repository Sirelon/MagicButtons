package com.sirelon.magicbuttons.feature.green

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal interface GreenContract {

    data class State(
        val buttonText: String,
        val counter: Int,
    )

    sealed interface Event {
        object ButtonClicked : Event
        object GoToBlueClicked : Event
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
            stateEmitter.emit(GreenContract.State(buttonText = "Старт", counter = 0))
        }
    }

    private fun initialState(): GreenContract.State =
        GreenContract.State(buttonText = "Старт", counter = 0)

    fun onEvent(event: GreenContract.Event) {
        when (event) {
            GreenContract.Event.ButtonClicked -> {
                stateEmitter.update { it.copy(counter = it.counter + 1) }
            }

            GreenContract.Event.GoToBlueClicked -> {
                setEffect { GreenContract.Effect.GoToBlue(counter = stateFlow.value.counter) }
            }
        }
    }

    private fun setEffect(effect: () -> GreenContract.Effect) {
        viewModelScope.launch {
            effectEmitter.send(effect())
        }
    }
}