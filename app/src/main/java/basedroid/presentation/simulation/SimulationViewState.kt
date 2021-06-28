package basedroid.presentation.simulation

import basedroid.domain.model.SimulationDomainModel

sealed class SimulationViewState {
    data class AmountFieldState(val validField: Boolean) : SimulationViewState()
    data class RateFieldState(val validField: Boolean) : SimulationViewState()
    data class DateFieldState(val validField: Boolean) : SimulationViewState()
    data class Success(val data: SimulationDomainModel) : SimulationViewState()
    data class ButtonState(val enabled: Boolean) : SimulationViewState()
    object Error : SimulationViewState()
}
