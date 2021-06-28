package basedroid.data.model

import com.google.gson.annotations.SerializedName

data class SimulationResponse(
    val investmentParameter: InvestmentParameterResponse,
    val grossAmount: Double,
    val taxesAmount: Double,
    val netAmount: Double,
    val grossAmountProfit: Double,
    val netAmountProfit: Double,
    val annualGrossRateProfit: Double,
    val monthlyGrossRateProfit: Double,
    val dailyGrossRateProfit: Double,
    val taxesRate: Double,
    val rateProfit: Double,
    val annualNetRateProfit: Double
) {
    data class InvestmentParameterResponse(
        @SerializedName("investedAmount")
        val investedAmount: Double,
        val yearlyInterestRate: Double,
        val maturityTotalDays: Int,
        val maturityBusinessDays: Int,
        val maturityDate: String,
        val rate: Double,
        val isTaxFree: Boolean
    )
}