package basedroid.domain.usecase

import basedroid.presentation.usecase.ValidDateUseCase
import basedroid.utils.validDay
import basedroid.utils.validMonth
import java.text.SimpleDateFormat
import java.util.*

class ValidDateUseCaseImpl : ValidDateUseCase {
    override suspend operator fun invoke(
        date: String
    ): Boolean {
        try {
            val openingDateCalendar =
                (Calendar.getInstance(Locale.getDefault()) as GregorianCalendar)
                    .apply { time = SimpleDateFormat("dd/MM/yyyy").parse(date) }
            val currentDateCalendar =
                (Calendar.getInstance(Locale.getDefault()) as GregorianCalendar)

            return when {
                !date.validMonth() -> false
                !date.validDay(openingDateCalendar.isLeapYear(openingDateCalendar.get(Calendar.YEAR))) -> false
                openingDateCalendar.get(Calendar.YEAR) <= currentDateCalendar.get(Calendar.YEAR) -> false
                else -> true
            }
        } catch (e: Exception) {
            return false
        }
    }
}
