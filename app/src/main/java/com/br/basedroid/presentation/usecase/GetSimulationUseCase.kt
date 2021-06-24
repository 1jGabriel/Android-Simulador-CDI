package com.br.basedroid.presentation.usecase

import com.br.basedroid.domain.model.SimulationDomainModel

interface GetSimulationUseCase {
    suspend operator fun invoke(
        investedAmount: Int,
        rate: Int,
        maturityDate: String
    ): SimulationDomainModel
}
