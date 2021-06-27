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

fun String.formatToServer(): String {
    val parts = this.split("/")
    return "${parts[2]}-${parts[1]}-${parts[0]}"
}

fun String.dateToUi(): String {
    val a = this.split("T")
    val b = a[0].split("-")
    return "${b[2]}/${b[1]}/${b[0]}"
}

fun String.validMonth(): Boolean {
    val month = this.substring(MONTH_START_INDEX, MONTH_END_INDEX).toInt()

    return month in 1..12
}

fun String.validDay(isLeapYear: Boolean): Boolean {
    val month = this.substring(MONTH_START_INDEX, MONTH_END_INDEX).toInt()
    val day = this.substring(DAY_START_INDEX, DAY_END_INDEX).toInt()

    return when {
        MONTHS_WITH_31.contains(month) -> day in 1..31
        MONTHS_WITH_30.contains(month) -> day in 1..30
        isLeapYear -> day in 1..29
        else -> day in 1..28
    }
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


val MONTHS_WITH_31 = listOf(1, 3, 5, 7, 8, 10, 12)
val MONTHS_WITH_30 = listOf(4, 6, 9, 11)
const val MONTH_START_INDEX = 3
const val MONTH_END_INDEX = 5
const val DAY_START_INDEX = 0
const val DAY_END_INDEX = 2
