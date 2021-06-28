package basedroid.presentation.usecase

interface ValidRateUseCase {
    suspend operator fun invoke(
        rate: String
    ): Boolean
}
