package basedroid.presentation.usecase

interface ValidDateUseCase {
    suspend operator fun invoke(
        date: String
    ): Boolean
}
