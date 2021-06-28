package basedroid.di

import basedroid.data.retrofit.HttpClient
import basedroid.data.retrofit.RetrofitClient
import basedroid.domain.mapper.SimulationResponseMapper
import basedroid.domain.repository.SimulationRepository
import basedroid.domain.usecase.GetSimulationUseCaseImpl
import basedroid.domain.usecase.ValidAmountUseCaseImpl
import basedroid.domain.usecase.ValidDateUseCaseImpl
import basedroid.domain.usecase.ValidRateUseCaseImpl
import basedroid.presentation.simulation.SimulationViewModel
import basedroid.presentation.usecase.GetSimulationUseCase
import basedroid.presentation.usecase.ValidAmountUseCase
import basedroid.presentation.usecase.ValidDateUseCase
import basedroid.presentation.usecase.ValidRateUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    factory<GetSimulationUseCase> { GetSimulationUseCaseImpl(get(), get()) }
    factory<ValidAmountUseCase> { ValidAmountUseCaseImpl() }
    factory<ValidDateUseCase> { ValidDateUseCaseImpl() }
    factory<ValidRateUseCase> { ValidRateUseCaseImpl() }
}

val presentationModules = module {
    viewModel { SimulationViewModel(get(), get(), get(), get()) }
}

val dataModules = module {
    factory<SimulationRepository> { basedroid.data.repository.SimulationRepositoryImpl(get()) }
}

val mapperModules = module {
    factory { SimulationResponseMapper() }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(basedroid.data.api.EasyApi::class.java) }
}

val anotherModules = module {}
