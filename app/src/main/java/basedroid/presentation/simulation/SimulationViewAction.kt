package basedroid.presentation.simulation

sealed class SimulationViewAction {
    object GetSimulation : SimulationViewAction()
    data class AmountChanged(val text: String) : SimulationViewAction()
    data class DateChanged(val text: String) : SimulationViewAction()
    data class RateChanged(val text: String) : SimulationViewAction()
}
