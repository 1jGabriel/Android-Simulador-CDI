package basedroid.domain.mapper

import basedroid.domain.model.SimulationDomainModel
import basedroid.utils.Mapper
import basedroid.utils.dateToUi
import basedroid.utils.percentageToUi
import basedroid.utils.toCurrency

class SimulationResponseMapper :
    Mapper<basedroid.data.model.SimulationResponse, SimulationDomainModel> {

    override fun map(from: basedroid.data.model.SimulationResponse) = with(from) {
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
