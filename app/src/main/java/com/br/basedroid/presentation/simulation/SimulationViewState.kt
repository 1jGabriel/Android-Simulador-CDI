package com.br.basedroid.presentation.simulation

import com.br.basedroid.domain.model.SimulationDomainModel

sealed class SimulationViewState {
    object Loading : SimulationViewState()
    object MissingValue : SimulationViewState()
    object MissingRate : SimulationViewState()
    object MissingDate : SimulationViewState()
    data class Success(val data: SimulationDomainModel) : SimulationViewState()
    object Error : SimulationViewState()
}
