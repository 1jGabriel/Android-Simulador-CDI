package basedroid.presentation.usecase

interface ValidAmountUseCase {
    suspend operator fun invoke(
        amount: String
    ): Boolean
}
