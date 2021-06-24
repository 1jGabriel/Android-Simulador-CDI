package com.br.basedroid.di

import com.br.basedroid.data.api.EasyApi
import com.br.basedroid.data.repository.SimulationRepositoryImpl
import com.br.basedroid.data.retrofit.HttpClient
import com.br.basedroid.data.retrofit.RetrofitClient
import com.br.basedroid.domain.mapper.SimulationResponseMapper
import com.br.basedroid.domain.repository.SimulationRepository
import com.br.basedroid.domain.usecase.GetSimulationUseCaseImpl
import com.br.basedroid.presentation.ui.SimulationViewModel
import com.br.basedroid.presentation.usecase.GetSimulationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    factory<GetSimulationUseCase> { GetSimulationUseCaseImpl(get(), get()) }
}

val presentationModules = module {
    viewModel { SimulationViewModel(get()) }
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
