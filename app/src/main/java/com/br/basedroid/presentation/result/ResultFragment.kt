package com.br.basedroid.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.br.basedroid.R
import com.br.basedroid.databinding.ResultFragmentBinding
import com.br.basedroid.domain.model.SimulationDomainModel

class ResultFragment : Fragment() {
    private lateinit var binding: ResultFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ResultFragmentBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = ResultFragmentArgs.fromBundle(requireArguments()).simulationResult
        binding.teste.text = args.toString()
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            findNavController().navigate(R.id.action_restart)
        }
    }
}