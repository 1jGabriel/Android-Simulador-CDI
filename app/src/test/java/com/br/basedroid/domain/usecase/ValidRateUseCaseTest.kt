package com.br.basedroid.domain.usecase

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidRateUseCaseTest {
    private val validRate = ValidRateUseCaseImpl()

    @Test
    fun `when pass an invalid rate result must be false`() = runBlockingTest {
        val result = validRate.invoke("99%")
        assertFalse { result }
    }

    @Test
    fun `when pass a valid rate result must be true`() = runBlockingTest {
        val result = validRate.invoke("100%")
        assertTrue { result }
    }
}