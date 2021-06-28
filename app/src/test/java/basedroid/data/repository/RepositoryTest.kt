package basedroid.data.repository

import basedroid.data.api.EasyApi
import basedroid.domain.repository.SimulationRepository
import basedroid.results.ResultFactory.simulationResponse
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
                investedAmount = 123.0,
                rate = 0,
                maturityDate = "",
            )
        ).thenReturn(
            simulationResponse
        )

        // When
        val result = repository.getSimulation(
            investedAmount = 123.0,
            rate = 0,
            maturityDate = ""
        )

        // Then
        verify(api).getSimulation(
            investedAmount = 123.0,
            rate = 0,
            maturityDate = ""
        )
        assertEquals(result, simulationResponse)
    }
}
