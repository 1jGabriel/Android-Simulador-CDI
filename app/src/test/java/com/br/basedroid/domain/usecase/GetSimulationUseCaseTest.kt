package com.br.basedroid.domain.usecase

import com.br.basedroid.domain.mapper.SimulationResponseMapper
import com.br.basedroid.domain.repository.SimulationRepository
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import com.br.basedroid.results.ResultFactory.simulationModel
import com.br.basedroid.results.ResultFactory.simulationResponse
import com.br.basedroid.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetSimulationUseCaseTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: SimulationRepository = mock()
    private val mapper: SimulationResponseMapper = mock()
    private val useCase: GetSimulationUseCase = GetSimulationUseCaseImpl(
        repository, mapper, coroutineRule.dispatcher
    )

    @Test
    fun `when invoke getSimulation should return success`() = runBlockingTest {
        whenever(
            repository.getSimulation(
                investedAmount = 0.0,
                rate = 0,
                maturityDate = ""
            )
        ).thenReturn(simulationResponse)

        whenever(
            mapper.map(simulationResponse)
        ).thenReturn(simulationModel)

        // When
        val result =
            useCase.invoke(investedAmount = 0.0, rate = 0, maturityDate = "")
        // Then
        assertEquals(result, simulationModel)
    }

}
