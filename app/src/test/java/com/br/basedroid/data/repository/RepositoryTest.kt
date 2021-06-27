package com.br.basedroid.data.repository

import com.br.basedroid.data.api.EasyApi
import com.br.basedroid.data.repository.SimulationRepositoryImpl
import com.br.basedroid.domain.repository.SimulationRepository
import com.br.basedroid.results.ResultFactory.simulationResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RepositoryTest {

    private val api: EasyApi = mock()
    private val repository: SimulationRepository by lazy {
        SimulationRepositoryImpl(api = api)
    }

    @Test
    fun `When call getSimulation from api should return success`() = runBlockingTest {
        // Given
        whenever(
            api.getSimulation(
                investedAmount = 123,
                rate = 0,
                maturityDate = "",
            )
        ).thenReturn(
            simulationResponse
        )

        // When
        val result = repository.getSimulation(
            investedAmount = 123,
            rate = 0,
            maturityDate = ""
        )

        // Then
        verify(api).getSimulation(
            investedAmount = 123,
            rate = 0,
            maturityDate = ""
        )
        assertEquals(result, simulationResponse)
    }
}
