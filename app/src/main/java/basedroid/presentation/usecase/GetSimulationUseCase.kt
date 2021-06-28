package basedroid.presentation.usecase

import basedroid.domain.model.SimulationDomainModel

interface GetSimulationUseCase {
    suspend operator fun invoke(
        investedAmount: Double,
        rate: Int,
        maturityDate: String
    ): SimulationDomainModel
}
