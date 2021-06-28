package com.br.basedroid.presentation.simulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.basedroid.R
import com.br.basedroid.databinding.SimulationFragmentBinding
import com.br.basedroid.domain.model.SimulationDomainModel
import com.br.basedroid.utils.AmountMask
import com.br.basedroid.utils.DateMask
import com.br.basedroid.utils.RateMask
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationFragment : Fragment() {
    private lateinit var binding: SimulationFragmentBinding

    private val viewModel: SimulationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SimulationFragmentBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.simulationSimulateBtn.setOnClickListener {
            viewModel.dispatchViewAction(
                SimulationViewAction.GetSimulation
            )
        }

        setupInputListeners()

        viewModel.viewState.observe(requireActivity(), {
            when (it) {
                is SimulationViewState.Error -> handleError()
                is SimulationViewState.DateFieldState -> handleMissingDate(it.validField)
                is SimulationViewState.RateFieldState -> handleMissingRate(it.validField)
                is SimulationViewState.AmountFieldState -> handleMissingValue(it.validField)
                is SimulationViewState.Success -> {
                    handleSuccess(it.data)
                }
                is SimulationViewState.ButtonState -> handleButtonState(it.enabled)
            }
        })
    }

    private fun handleButtonState(enabled: Boolean) {
        binding.simulationSimulateBtn.isEnabled = enabled
    }

    private fun setupInputListeners() {

        binding.simulationAmountInput.apply {
            doOnTextChanged { text, _, _, _ ->
                viewModel.dispatchViewAction(SimulationViewAction.AmountChanged(text.toString()))
            }
            addTextChangedListener(
                AmountMask()
            )
        }

        binding.simulationRateInput.apply {
            doOnTextChanged { text, _, _, _ ->
                viewModel.dispatchViewAction(SimulationViewAction.RateChanged(text.toString()))
            }
            addTextChangedListener(
                RateMask()
            )
        }

        binding.simulationMaturityDateInput.apply {
            doOnTextChanged { text, _, _, _ ->
                viewModel.dispatchViewAction(SimulationViewAction.DateChanged(text.toString()))
            }
            addTextChangedListener(
                DateMask()
            )
        }

    }

    private fun handleError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.simulation_error_msg),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun handleMissingDate(validField: Boolean) {
        if (validField) {
            binding.simulationMaturityDateInput.error = null
        } else {
            binding.simulationMaturityDateInput.error = getString(R.string.simulation_date_error)
        }
    }

    private fun handleMissingRate(validField: Boolean) {
        if (validField) {
            binding.simulationRateInput.error = null
        } else {
            binding.simulationRateInput.error = getString(R.string.simulation_rate_error)
        }
    }

    private fun handleMissingValue(validField: Boolean) {
        if (validField) {
            binding.simulationAmountInput.error = null
        } else {
            binding.simulationAmountInput.error = getString(R.string.simulation_value_error)
        }
    }

    private fun handleSuccess(it: SimulationDomainModel) {
        val action = SimulationFragmentDirections.actionShowResult().apply {
            simulationResult = it
        }
        this@SimulationFragment.findNavController()
            .navigate(action)
    }
}