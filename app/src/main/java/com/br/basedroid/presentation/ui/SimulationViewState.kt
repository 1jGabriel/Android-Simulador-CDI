package com.br.basedroid.presentation.ui

sealed class SimulationViewState {
    object Loading : SimulationViewState()
    object MissingValue : SimulationViewState()
    object MissingRate : SimulationViewState()
    object MissingDate : SimulationViewState()
    object Success : SimulationViewState()
    object Error : SimulationViewState()
}
