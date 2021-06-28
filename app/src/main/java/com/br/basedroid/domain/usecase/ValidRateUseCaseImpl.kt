package com.br.basedroid.domain.usecase

import com.br.basedroid.presentation.usecase.ValidRateUseCase
import com.br.basedroid.utils.percentageToDouble

class ValidRateUseCaseImpl : ValidRateUseCase {
    override suspend operator fun invoke(
        rate: String
    ): Boolean {
        return rate.percentageToDouble() >= 100
    }
}
