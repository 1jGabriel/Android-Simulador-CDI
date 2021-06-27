package com.br.basedroid.domain.usecase

import com.br.basedroid.presentation.usecase.ValidAmountUseCase
import com.br.basedroid.utils.cleanCurrencyCharacters

class ValidAmountUseCaseImpl : ValidAmountUseCase {
    override suspend operator fun invoke(
        amount: String
    ): Boolean {
        return amount.cleanCurrencyCharacters().toDouble() > 0
    }
}
