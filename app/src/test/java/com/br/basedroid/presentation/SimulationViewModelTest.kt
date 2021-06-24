package com.br.basedroid.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.basedroid.presentation.simulation.SimulationViewAction
import com.br.basedroid.presentation.simulation.SimulationViewModel
import com.br.basedroid.presentation.simulation.SimulationViewState
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import com.br.basedroid.results.ResultFactory
import com.br.basedroid.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SimulationViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetSimulationUseCase = mock()
    private val viewModel = SimulationViewModel(useCase)

    @Test
    fun `when dispatch GetSimulationAction should call use case`() = runBlockingTest {
        // Given
        whenever(
            useCase.invoke(0, 0, "")
        ).thenReturn(ResultFactory.simulationModel)

        // When
        viewModel.dispatchViewAction(
            SimulationViewAction.GetSimulation(
                investedAmount = 0,
                rate = 0,
                maturityDate = ""
            )
        )
        // Then
        verify(useCase).invoke(
            investedAmount = 0,
            rate = 0,
            maturityDate = ""
        )
        assertTrue(viewModel.viewState.value is SimulationViewState.Success)
    }
}
