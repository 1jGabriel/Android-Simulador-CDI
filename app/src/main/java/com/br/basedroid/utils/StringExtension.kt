package com.br.basedroid.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun String.twoDecimals(): String {
    return when (this.length) {
        1 -> "0$this"
        2 -> this
        else -> this.substring(0, 2)
    }
}

fun String.formatMaturityDate(): String {
    val parts = this.split("/")
    return "${parts[2]}-${parts[1]}-${parts[0]}"
}

fun String.dateToUi(): String {
    val a = this.split("T")
    val b = a[0].split("-")
    return "${b[2]}/${b[1]}/${b[0]}"
}

fun String.percentageToDouble() = this.replace(",", ";")
    .replace(".", "")
    .replace(";", ".")
    .replace("%", "")
    .toDouble()


fun String.formatPercentage(): String {
    val number = this.replace("%", "")
        .replace(",", ";")
        .replace(".", "")
        .replace(";", ".")
        .toDouble()
    val decimal = BigDecimal(number).setScale(2, RoundingMode.HALF_EVEN)
    val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val currency = numberFormat.format((decimal.toDouble()))
    return currency.replace(",", "")
        .replace("$", "")
}

fun String.cleanCurrencyCharacters(): String {
    return this.replace("R", "")
        .replace("$", "")
        .replace(".", "")
        .replace(",", ".")
        .replace(" ", "")
}