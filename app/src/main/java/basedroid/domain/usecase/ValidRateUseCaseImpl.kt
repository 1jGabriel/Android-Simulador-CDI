package basedroid.domain.usecase

import basedroid.presentation.usecase.ValidRateUseCase
import basedroid.utils.percentageToDouble

class ValidRateUseCaseImpl : ValidRateUseCase {
    override suspend operator fun invoke(
        rate: String
    ): Boolean {
        return rate.percentageToDouble() >= 100
    }
}
