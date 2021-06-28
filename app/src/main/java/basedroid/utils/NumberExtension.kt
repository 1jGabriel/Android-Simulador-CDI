package basedroid.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun Double.percentageToUi(): String {
    val a = this.toString().split(".")
    return "${a[0]},${a[1].twoDecimals()}%"
}

fun Double.toCurrency(includeCurrencySign: Boolean = true): String {
    val decimal = BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN)
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    var output = numberFormat.format((decimal.toDouble()))
    output = output.replace(".", ";")
        .replace(",", ".")
        .replace(";", ",")
        .replace("$", "")
    return if (includeCurrencySign) "R$ $output" else "${output.replace(" ", "")}"
}
