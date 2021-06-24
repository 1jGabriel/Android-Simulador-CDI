package com.br.basedroid.data.repository

import com.br.basedroid.data.api.EasyApi
import com.br.basedroid.domain.repository.SimulationRepository

class SimulationRepositoryImpl(
    private val api: EasyApi
) : SimulationRepository {

    override suspend fun getSimulation(
        investedAmount: Int,
        rate: Int,
        maturityDate: String
    ) = api.getSimulation(
        investedAmount = investedAmount,
        rate = rate,
        maturityDate = maturityDate
    )
}
