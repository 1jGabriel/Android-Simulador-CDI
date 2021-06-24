package com.br.basedroid.presentation.simulation

sealed class SimulationViewAction {
    data class GetSimulation(
        val investedAmount: Int,
        val rate: Int,
        val maturityDate: String
    ) : SimulationViewAction()
}
