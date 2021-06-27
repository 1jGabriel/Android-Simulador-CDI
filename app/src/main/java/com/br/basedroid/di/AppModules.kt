package com.br.basedroid.di

import com.br.basedroid.data.api.EasyApi
import com.br.basedroid.data.repository.SimulationRepositoryImpl
import com.br.basedroid.data.retrofit.HttpClient
import com.br.basedroid.data.retrofit.RetrofitClient
import com.br.basedroid.domain.mapper.SimulationResponseMapper
import com.br.basedroid.domain.repository.SimulationRepository
import com.br.basedroid.domain.usecase.GetSimulationUseCaseImpl
import com.br.basedroid.domain.usecase.ValidAmountUseCaseImpl
import com.br.basedroid.domain.usecase.ValidDateUseCaseImpl
import com.br.basedroid.domain.usecase.ValidRateUseCaseImpl
import com.br.basedroid.presentation.simulation.SimulationViewModel
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import com.br.basedroid.presentation.usecase.ValidAmountUseCase
import com.br.basedroid.presentation.usecase.ValidDateUseCase
import com.br.basedroid.presentation.usecase.ValidRateUseCase
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
    factory<SimulationRepository> { SimulationRepositoryImpl(get()) }
}

val mapperModules = module {
    factory { SimulationResponseMapper() }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(EasyApi::class.java) }
}

val anotherModules = module {}
