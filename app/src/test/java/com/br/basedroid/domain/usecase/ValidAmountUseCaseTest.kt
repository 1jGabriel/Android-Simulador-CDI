package com.br.basedroid.domain.usecase

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidAmountUseCaseTest {
    private val validAmount = ValidAmountUseCaseImpl()

    @Test
    fun `when pass an invalid amount result must be false`() = runBlockingTest {
        val result = validAmount.invoke("0")
        assertFalse { result }
    }

    @Test
    fun `when pass a valid amount result must be true`() = runBlockingTest {
        val result = validAmount.invoke("R$ 50,00")
        assertTrue { result }
    }
}