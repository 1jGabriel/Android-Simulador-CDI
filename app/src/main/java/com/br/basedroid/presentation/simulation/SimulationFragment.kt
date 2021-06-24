package com.br.basedroid.presentation.simulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.basedroid.R
import com.br.basedroid.databinding.SimulationFragmentBinding
import com.br.basedroid.presentation.result.ResultFragmentDirections
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

        viewModel.dispatchViewAction(
            SimulationViewAction.GetSimulation(
                investedAmount = 0,
                rate = 0,
                maturityDate = ""
            )
        )

        viewModel.viewState.observe(requireActivity(), {
            when (it) {
                is SimulationViewState.Error -> TODO()
                is SimulationViewState.Loading -> TODO()
                is SimulationViewState.MissingDate -> TODO()
                is SimulationViewState.MissingRate -> TODO()
                is SimulationViewState.MissingValue -> TODO()
                is SimulationViewState.Success -> {
                    val action = SimulationFragmentDirections.actionShowResult().apply {
                        simulationResult = it.data
                    }
                    this@SimulationFragment.findNavController()
                        .navigate(action)
                }
            }
        })
    }
}