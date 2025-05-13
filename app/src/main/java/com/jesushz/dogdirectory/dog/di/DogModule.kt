package com.jesushz.dogdirectory.dog.di

import com.jesushz.dogdirectory.dog.data.network.KtorRemoteDogDataSource
import com.jesushz.dogdirectory.dog.data.network.RemoteDogDataSource
import com.jesushz.dogdirectory.dog.data.repository.DefaultDogRepository
import com.jesushz.dogdirectory.dog.domain.repository.DogRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dogModule = module {
    singleOf(::KtorRemoteDogDataSource).bind<RemoteDogDataSource>()
    singleOf(::DefaultDogRepository).bind<DogRepository>()
}
