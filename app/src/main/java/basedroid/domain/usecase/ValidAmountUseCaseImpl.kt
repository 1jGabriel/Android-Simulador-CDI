package basedroid.domain.usecase

import basedroid.presentation.usecase.ValidAmountUseCase
import basedroid.utils.cleanCurrencyCharacters

class ValidAmountUseCaseImpl : ValidAmountUseCase {
    override suspend operator fun invoke(
        amount: String
    ): Boolean {
        return amount.cleanCurrencyCharacters().toDouble() > 0
    }
}
