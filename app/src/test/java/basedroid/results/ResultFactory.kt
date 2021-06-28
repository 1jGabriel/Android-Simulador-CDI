package basedroid.results

import basedroid.domain.model.SimulationDomainModel

object ResultFactory {
    val simulationResponse = basedroid.data.model.SimulationResponse(
        investmentParameter = basedroid.data.model.SimulationResponse.InvestmentParameterResponse(
            investedAmount = 0.0,
            yearlyInterestRate = 0.0,
            maturityTotalDays = 0,
            maturityBusinessDays = 0,
            maturityDate = "2023-03-03T00:00:00",
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
            investedAmount = "R$ 0,00",
            yearlyInterestRate = "0,00%",
            maturityTotalDays = 0,
            maturityBusinessDays = 0,
            maturityDate = "03/03/2023",
            rate = "0,00%",
            isTaxFree = false
        ),
        grossAmount = "R$ 0,00",
        taxesAmount = "R$ 0,00",
        netAmount = "R$ 0,00",
        grossAmountProfit = "R$ 0,00",
        netAmountProfit = "R$ 0,00",
        annualGrossRateProfit = "0,00%",
        monthlyGrossRateProfit = "0,00%",
        dailyGrossRateProfit = "0,00%",
        taxesRate = "0,00%",
        rateProfit = "0,00%",
        annualNetRateProfit = "0,00%"
    )
}
