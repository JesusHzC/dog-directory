package com.jesushz.dogdirectory.dog.di

import com.jesushz.dogdirectory.dog.data.database.RoomLocalDogDataSource
import com.jesushz.dogdirectory.dog.data.network.KtorRemoteDogDataSource
import com.jesushz.dogdirectory.dog.domain.network.RemoteDogDataSource
import com.jesushz.dogdirectory.dog.data.repository.DefaultDogRepository
import com.jesushz.dogdirectory.dog.domain.local.LocalDogDataSource
import com.jesushz.dogdirectory.dog.domain.repository.DogRepository
import com.jesushz.dogdirectory.dog.presentation.dog_detail.DogDetailViewModel
import com.jesushz.dogdirectory.dog.presentation.dog_list.DogListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dogModule = module {
    singleOf(::KtorRemoteDogDataSource).bind<RemoteDogDataSource>()
    singleOf(::DefaultDogRepository).bind<DogRepository>()

    viewModelOf(::DogListViewModel)
    viewModelOf(::DogDetailViewModel)

    singleOf(::RoomLocalDogDataSource).bind<LocalDogDataSource>()
}
