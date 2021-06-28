package com.br.basedroid.domain.repository

import com.br.basedroid.data.model.SimulationResponse

interface SimulationRepository {
    suspend fun getSimulation(
        investedAmount: Double,
        rate: Int,
        maturityDate: String
    ): SimulationResponse
}
