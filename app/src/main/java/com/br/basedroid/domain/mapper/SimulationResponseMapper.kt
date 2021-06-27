package com.br.basedroid.domain.mapper

import com.br.basedroid.data.model.SimulationResponse
import com.br.basedroid.domain.model.SimulationDomainModel
import com.br.basedroid.utils.Mapper
import com.br.basedroid.utils.dateToUi
import com.br.basedroid.utils.toCurrency
import com.br.basedroid.utils.percentageToUi

class SimulationResponseMapper : Mapper<SimulationResponse, SimulationDomainModel> {

    override fun map(from: SimulationResponse) = with(from) {
        SimulationDomainModel(
            investmentParameter = SimulationDomainModel.InvestmentParameter(
                investedAmount = investmentParameter.investedAmount.toCurrency(),
                yearlyInterestRate = investmentParameter.yearlyInterestRate.percentageToUi(),
                maturityTotalDays = investmentParameter.maturityTotalDays,
                maturityBusinessDays = investmentParameter.maturityBusinessDays,
                maturityDate = investmentParameter.maturityDate.dateToUi(),
                rate = investmentParameter.rate.percentageToUi(),
                isTaxFree = investmentParameter.isTaxFree
            ),
            grossAmount = grossAmount.toCurrency(),
            taxesAmount = taxesAmount.toCurrency(),
            netAmount = netAmount.toCurrency(),
            grossAmountProfit = grossAmountProfit.toCurrency(),
            netAmountProfit = netAmountProfit.toCurrency(),
            annualGrossRateProfit = annualGrossRateProfit.percentageToUi(),
            monthlyGrossRateProfit = monthlyGrossRateProfit.percentageToUi(),
            dailyGrossRateProfit = dailyGrossRateProfit.percentageToUi(),
            taxesRate = taxesRate.percentageToUi(),
            rateProfit = rateProfit.percentageToUi(),
            annualNetRateProfit = annualNetRateProfit.percentageToUi()

        )
    }
}
