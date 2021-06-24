package com.br.basedroid.domain.mapper

import com.br.basedroid.data.model.SimulationResponse
import com.br.basedroid.domain.model.SimulationDomainModel
import com.br.basedroid.utils.Mapper

class SimulationResponseMapper : Mapper<SimulationResponse, SimulationDomainModel> {

    override fun map(from: SimulationResponse) = with(from) {
        SimulationDomainModel(
            investmentParameter = SimulationDomainModel.InvestmentParameter(
                investedAmount = investmentParameter.investedAmount,
                yearlyInterestRate = investmentParameter.yearlyInterestRate,
                maturityTotalDays = investmentParameter.maturityTotalDays,
                maturityBusinessDays = investmentParameter.maturityBusinessDays,
                maturityDate = investmentParameter.maturityDate,
                rate = investmentParameter.rate,
                isTaxFree = investmentParameter.isTaxFree
            ),
            grossAmount = grossAmount,
            taxesAmount = taxesAmount,
            netAmount = netAmount,
            grossAmountProfit = grossAmountProfit,
            netAmountProfit = netAmountProfit,
            annualGrossRateProfit = annualGrossRateProfit,
            monthlyGrossRateProfit = monthlyGrossRateProfit,
            dailyGrossRateProfit = dailyGrossRateProfit,
            taxesRate = taxesRate,
            rateProfit = rateProfit,
            annualNetRateProfit = annualNetRateProfit

        )
    }
}
