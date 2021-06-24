package com.br.basedroid.domain.mapper

import com.br.basedroid.results.ResultFactory.simulationModel
import com.br.basedroid.results.ResultFactory.simulationResponse
import org.junit.Test
import kotlin.test.assertEquals

class SimulationResponseMapperTest {

    private val mapper: SimulationResponseMapper = SimulationResponseMapper()

    @Test
    fun `when call map mapper should map to domain model`() {
        // When
        val result = mapper.map(simulationResponse)

        // Then
        assertEquals(result, simulationModel)
    }
}