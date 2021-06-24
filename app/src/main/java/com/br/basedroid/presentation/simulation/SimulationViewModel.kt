package com.br.basedroid.presentation.simulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import kotlinx.coroutines.launch

class SimulationViewModel(
    private val getSimulation: GetSimulationUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<SimulationViewState>()

    val viewState: LiveData<SimulationViewState>
        get() = _viewState

    fun dispatchViewAction(action: SimulationViewAction) {
        when (action) {
            is SimulationViewAction.GetSimulation -> handleGetSimulation(
                action.investedAmount,
                action.maturityDate,
                action.rate
            )
        }
    }

    private fun handleGetSimulation(
        investedAmount: Int,
        maturityDate: String,
        rate: Int
    ) {
        viewModelScope.launch {
            // TODO : Add use case to check if values are greater than 0
            runCatching {
                getSimulation(
                    investedAmount = investedAmount,
                    rate = rate,
                    maturityDate = maturityDate
                )
            }.onSuccess {
                _viewState.postValue(SimulationViewState.Success(it))
            }.onFailure {
                _viewState.postValue(SimulationViewState.Error)
            }
        }
    }
}
