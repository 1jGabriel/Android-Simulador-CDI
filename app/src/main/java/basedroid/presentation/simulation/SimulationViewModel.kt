package basedroid.presentation.simulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import basedroid.presentation.usecase.GetSimulationUseCase
import basedroid.presentation.usecase.ValidAmountUseCase
import basedroid.presentation.usecase.ValidDateUseCase
import basedroid.presentation.usecase.ValidRateUseCase
import basedroid.utils.cleanCurrencyCharacters
import basedroid.utils.formatToServer
import basedroid.utils.percentageToDouble
import kotlinx.coroutines.launch

class SimulationViewModel(
    private val getSimulation: GetSimulationUseCase,
    private val validRate: ValidRateUseCase,
    private val validDate: ValidDateUseCase,
    private val validAmount: ValidAmountUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<SimulationViewState>()
    private var date = ""
    private var amount = ""
    private var rate = ""

    val viewState: LiveData<SimulationViewState>
        get() = _viewState

    fun dispatchViewAction(action: SimulationViewAction) {
        when (action) {
            is SimulationViewAction.GetSimulation -> handleGetSimulation()
            is SimulationViewAction.AmountChanged -> handleAmountChanged(action.text)
            is SimulationViewAction.DateChanged -> handleDateChanged(action.text)
            is SimulationViewAction.RateChanged -> handleRateChanged(action.text)
        }
    }

    private fun handleAmountChanged(text: String) {
        viewModelScope.launch {
            if (validAmount(text)) {
                amount = text
                _viewState.value = SimulationViewState.AmountFieldState(true)
            } else {
                amount = ""
                _viewState.value = SimulationViewState.AmountFieldState(false)
            }
            checkFields()
        }
    }

    private fun handleDateChanged(text: String) {
        viewModelScope.launch {
            if (validDate(text)) {
                _viewState.value = SimulationViewState.DateFieldState(true)
                date = text
            } else {
                date = ""
                _viewState.value = SimulationViewState.DateFieldState(false)
            }

            checkFields()
        }
    }

    private fun handleRateChanged(text: String) {
        viewModelScope.launch {
            if (validRate(text)) {
                rate = text
                _viewState.value = SimulationViewState.RateFieldState(true)
            } else {
                rate = ""
                _viewState.value = SimulationViewState.RateFieldState(false)
            }
            checkFields()
        }
    }

    private fun checkFields() {
        _viewState.value =
            SimulationViewState.ButtonState(
                rate.isNotEmpty() && date.isNotEmpty() && amount.isNotEmpty()
            )
    }

    private fun handleGetSimulation() {
        viewModelScope.launch {
            runCatching {
                getSimulation(
                    investedAmount = amount.cleanCurrencyCharacters().toDouble(),
                    rate = rate.percentageToDouble().toInt(),
                    maturityDate = date.formatToServer()
                )
            }.onSuccess {
                _viewState.postValue(SimulationViewState.Success(it))
            }.onFailure {
                _viewState.postValue(SimulationViewState.Error)
            }
        }
    }
}
