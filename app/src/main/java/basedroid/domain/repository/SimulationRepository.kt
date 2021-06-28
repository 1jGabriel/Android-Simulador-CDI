package basedroid.domain.repository

import basedroid.data.model.SimulationResponse

interface SimulationRepository {
    suspend fun getSimulation(
        investedAmount: Double,
        rate: Int,
        maturityDate: String
    ): SimulationResponse
}
