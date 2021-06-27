package com.br.basedroid.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun Long.dateIsInFuture(): Boolean {
    val current = Date(System.currentTimeMillis())
    val diff: Long = this - current.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    return days > 0
}


fun Double.formatPercentage(): String {
    val decimal = BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN)
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val currency = numberFormat.format((decimal.toDouble()))
    return currency.replace(".", ";")
        .replace(",", ".")
        .replace(";", ",")
        .replace("$", "")
}

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
