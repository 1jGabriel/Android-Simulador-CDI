package com.br.basedroid.presentation.usecase

interface ValidAmountUseCase {
    suspend operator fun invoke(
        amount: String
    ): Boolean
}
