package basedroid.data.repository

import basedroid.domain.repository.SimulationRepository


class SimulationRepositoryImpl(
    private val api: basedroid.data.api.EasyApi
) : SimulationRepository {

    override suspend fun getSimulation(
        investedAmount: Double,
        rate: Int,
        maturityDate: String
    ) = api.getSimulation(
        investedAmount = investedAmount,
        rate = rate,
        maturityDate = maturityDate
    )
}
