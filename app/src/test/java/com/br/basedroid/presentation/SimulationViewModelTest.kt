package com.br.basedroid.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.basedroid.presentation.simulation.SimulationViewAction
import com.br.basedroid.presentation.simulation.SimulationViewModel
import com.br.basedroid.presentation.simulation.SimulationViewState
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import com.br.basedroid.presentation.usecase.ValidAmountUseCase
import com.br.basedroid.presentation.usecase.ValidDateUseCase
import com.br.basedroid.presentation.usecase.ValidRateUseCase
import com.br.basedroid.results.ResultFactory
import com.br.basedroid.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SimulationViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getSimulation: GetSimulationUseCase = mock()
    private val validRate: ValidRateUseCase = mock()
    private val validDate: ValidDateUseCase = mock()
    private val validAmount: ValidAmountUseCase = mock()
    private val viewModel = SimulationViewModel(
        getSimulation, validRate, validDate, validAmount
    )

    private val validAmountInput = "R$ 1,00"
    private val invalidAmountInput = "R$ 0"
    private val amountExpected = 1.0
    private val validRateInput = "120%"
    private val invalidRateInput = "99%"
    private val rateExpected = 120
    private val validDateInput = "13/08/2023"
    private val invalidDateInput = "13/08/2020"
    private val dateExpected = "2023-08-13"

    @Test
    fun `when dispatch GetSimulationAction should call use case`() = runBlockingTest {
        // Given
        whenever(validAmount.invoke(validAmountInput)).thenReturn(true)
        whenever(validRate.invoke(validRateInput)).thenReturn(true)
        whenever(validDate.invoke(validDateInput)).thenReturn(true)

        whenever(
            getSimulation.invoke(amountExpected, rateExpected, dateExpected)
        ).thenReturn(ResultFactory.simulationModel)

        // When

        viewModel.dispatchViewAction(SimulationViewAction.AmountChanged(validAmountInput))
        viewModel.dispatchViewAction(SimulationViewAction.RateChanged(validRateInput))
        viewModel.dispatchViewAction(SimulationViewAction.DateChanged(validDateInput))
        viewModel.dispatchViewAction(SimulationViewAction.GetSimulation)

        // Then
        verify(getSimulation).invoke(
            investedAmount = amountExpected,
            rate = rateExpected,
            maturityDate = dateExpected
        )
        assertTrue(viewModel.viewState.value is SimulationViewState.Success)
    }

    @Test
    fun `when dispatch GetSimulationAction should return error`() = runBlockingTest {
        // Given
        whenever(validAmount.invoke(validAmountInput)).thenReturn(true)
        whenever(validRate.invoke(validRateInput)).thenReturn(true)
        whenever(validDate.invoke(validDateInput)).thenReturn(true)

        whenever(
            getSimulation.invoke(amountExpected, rateExpected, dateExpected)
        ).thenThrow()

        // When

        viewModel.dispatchViewAction(SimulationViewAction.AmountChanged(validAmountInput))
        viewModel.dispatchViewAction(SimulationViewAction.RateChanged(validRateInput))
        viewModel.dispatchViewAction(SimulationViewAction.DateChanged(validDateInput))
        viewModel.dispatchViewAction(SimulationViewAction.GetSimulation)

        // Then
        verify(getSimulation).invoke(
            investedAmount = amountExpected,
            rate = rateExpected,
            maturityDate = dateExpected
        )
        assertTrue(viewModel.viewState.value is SimulationViewState.Error)
    }

    @Test
    fun `when call amount changed given a invalid value then button state must be disabled`() =
        runBlockingTest {
            whenever(validAmount.invoke(invalidAmountInput)).thenReturn(false)
            viewModel.dispatchViewAction(SimulationViewAction.AmountChanged(invalidAmountInput))

            assertFalse {
                (viewModel.viewState.value as SimulationViewState.ButtonState).enabled
            }
        }

    @Test
    fun `when call date changed given a invalid date then button state must be disabled`() =
        runBlockingTest {
            whenever(validDate.invoke(invalidDateInput)).thenReturn(false)
            viewModel.dispatchViewAction(SimulationViewAction.DateChanged(invalidDateInput))

            assertFalse {
                (viewModel.viewState.value as SimulationViewState.ButtonState).enabled
            }
        }

    @Test
    fun `when call rate changed given a invalid rate then button state must be disabled`() =
        runBlockingTest {
            whenever(validRate.invoke(invalidRateInput)).thenReturn(false)
            viewModel.dispatchViewAction(SimulationViewAction.RateChanged(invalidRateInput))

            assertFalse {
                (viewModel.viewState.value as SimulationViewState.ButtonState).enabled
            }
        }
}
