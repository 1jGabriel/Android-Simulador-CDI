package basedroid.utils

import org.junit.Test
import kotlin.test.assertTrue

class NumberExtensionTest {
    @Test
    fun `given a double type value then should transform to Ui format`() {
        val given = 22.0
        val expected = "22,00%"
        val result = given.percentageToUi()
        assertTrue { result == expected }
    }

    @Test
    fun `given a double type value then should transform to currency format`() {
        val given = 22.0
        val expected = "R$ 22,00"
        val result = given.toCurrency()
        assertTrue { result == expected }
    }
}
