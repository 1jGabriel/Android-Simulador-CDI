package basedroid.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimulationDomainModel(
    val investmentParameter: InvestmentParameter,
    val grossAmount: String,
    val taxesAmount: String,
    val netAmount: String,
    val grossAmountProfit: String,
    val netAmountProfit: String,
    val annualGrossRateProfit: String,
    val monthlyGrossRateProfit: String,
    val dailyGrossRateProfit: String,
    val taxesRate: String,
    val rateProfit: String,
    val annualNetRateProfit: String
) : Parcelable {
    @Parcelize
    data class InvestmentParameter(
        val investedAmount: String,
        val yearlyInterestRate: String,
        val maturityTotalDays: Int,
        val maturityBusinessDays: Int,
        val maturityDate: String,
        val rate: String,
        val isTaxFree: Boolean
    ) : Parcelable
}
