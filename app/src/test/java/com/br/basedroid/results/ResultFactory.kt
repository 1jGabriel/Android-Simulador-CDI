package com.br.basedroid.results

import com.br.basedroid.data.model.SimulationResponse
import com.br.basedroid.domain.model.SimulationDomainModel

object ResultFactory {
    val simulationResponse = SimulationResponse(
        investmentParameter = SimulationResponse.InvestmentParameterResponse(
            investedAmount = 0.0,
            yearlyInterestRate = 0.0,
            maturityTotalDays = 0,
            maturityBusinessDays = 0,
            maturityDate = "",
            rate = 0.0,
            isTaxFree = false
        ),
        grossAmount = 0.0,
        taxesAmount = 0.0,
        netAmount = 0.0,
        grossAmountProfit = 0.0,
        netAmountProfit = 0.0,
        annualGrossRateProfit = 0.0,
        monthlyGrossRateProfit = 0.0,
        dailyGrossRateProfit = 0.0,
        taxesRate = 0.0,
        rateProfit = 0.0,
        annualNetRateProfit = 0.0
    )

    val simulationModel = SimulationDomainModel(
        investmentParameter = SimulationDomainModel.InvestmentParameter(
            investedAmount = 0.0,
            yearlyInterestRate = 0.0,
            maturityTotalDays = 0,
            maturityBusinessDays = 0,
            maturityDate = "",
            rate = 0.0,
            isTaxFree = false
        ),
        grossAmount = 0.0,
        taxesAmount = 0.0,
        netAmount = 0.0,
        grossAmountProfit = 0.0,
        netAmountProfit = 0.0,
        annualGrossRateProfit = 0.0,
        monthlyGrossRateProfit = 0.0,
        dailyGrossRateProfit = 0.0,
        taxesRate = 0.0,
        rateProfit = 0.0,
        annualNetRateProfit = 0.0
    )
}
