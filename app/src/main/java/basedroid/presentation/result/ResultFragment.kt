package basedroid.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import basedroid.R
import basedroid.domain.model.SimulationDomainModel
import basedroid.databinding.ResultFragmentBinding

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
        setupScreenValues(args)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            findNavController().navigate(R.id.action_restart)
        }
        setupListener()
    }

    private fun setupListener() {
        binding.resultResultSimulateAgainBtn.setOnClickListener {
            findNavController().navigate(R.id.action_restart)
        }
    }

    private fun setupScreenValues(args: SimulationDomainModel) {
        with(args) {
            binding.apply {
                resultInitialAmountValue.text = investmentParameter.investedAmount
                resultFinalAmountValue.text = grossAmount
                resultGrossValue.text = grossAmount
                resultFinalProfitAmountValue.text = grossAmountProfit
                resultTaxesAmountValue.text = taxesAmount
                resultNetAmountValue.text = netAmount
                resultMaturityDateValue.text = investmentParameter.maturityDate
                resultMaturityDaysValue.text = investmentParameter.maturityTotalDays.toString()
                resultMonthlyProfitValue.text = monthlyGrossRateProfit
                resultRateValue.text = investmentParameter.rate
                resultAnnualProfitValue.text = annualGrossRateProfit
                resultPeriodProfitValue.text = rateProfit
                resultGrossValue.text = grossAmount
                resultProfitValue.text = grossAmountProfit

            }
        }
    }
}