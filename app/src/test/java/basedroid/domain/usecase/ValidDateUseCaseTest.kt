package basedroid.domain.usecase

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidDateUseCaseTest {
    private val validDate = ValidDateUseCaseImpl()

    @Test
    fun `when pass an invalid date result must be false`() = runBlockingTest {
        val result = validDate.invoke("13/08/2010")
        assertFalse { result }
    }

    @Test
    fun `when pass a valid date result must be true`() = runBlockingTest {
        val result = validDate.invoke("13/08/2022")
        assertTrue { result }
    }
}