package com.br.basedroid.utils

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringExtensionTest {
    @Test
    fun `should transform a given string into a number with two decimals`() {
        val given = "22,50"
        val expected = "22"
        val result = given.twoDecimals()
        assertTrue { result == expected }
    }

    @Test
    fun `must transform a given date into server format`() {
        val given = "13/06/1993"
        val expected = "1993-06-13"
        val result = given.formatToServer()
        assertTrue { result == expected }
    }

    @Test
    fun `must transform a given date into ui format`() {
        val given = "2023-03-03T00:00:00"
        val expected = "03/03/2023"
        val result = given.dateToUi()
        assertTrue { result == expected }
    }

    @Test
    fun `must validate if month number is between 1 and 12`() {
        val given = "11/05/2020"
        val secondCase = "11/13/2021"
        assertTrue { given.validMonth() }
        assertFalse { secondCase.validMonth() }
    }

    @Test
    fun `given a string with ui percentage format should transform into a double number`() {
        val given = "23,00%"
        val expected = 23.0
        val result = given.percentageToDouble()
        assertTrue { result == expected }
    }

    @Test
    fun `given a string with ui currency format should return a string without currency chars`() {
        val given = "R$ 23,00"
        val expected = "23.00"
        val result = given.cleanCurrencyCharacters()
        assertTrue { expected == result }
    }

}
