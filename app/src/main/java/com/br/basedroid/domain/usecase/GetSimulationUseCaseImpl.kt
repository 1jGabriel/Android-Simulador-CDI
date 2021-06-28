package com.br.basedroid.domain.usecase

import com.br.basedroid.domain.mapper.SimulationResponseMapper
import com.br.basedroid.domain.repository.SimulationRepository
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSimulationUseCaseImpl(
    private val repository: SimulationRepository,
    private val simulationMapper: SimulationResponseMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetSimulationUseCase {

    override suspend operator fun invoke(
        investedAmount: Double,
        rate: Int,
        maturityDate: String
    ) = withContext(dispatcher) {
        try {
            simulationMapper.map(
                repository.getSimulation(
                    investedAmount = investedAmount,
                    rate = rate,
                    maturityDate = maturityDate
                )
            )
        } catch (e: Exception) {
            throw e
        }
    }
}
