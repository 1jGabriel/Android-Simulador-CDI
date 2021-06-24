package com.br.basedroid.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.br.basedroid.databinding.SimulationFragmentBinding
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


}