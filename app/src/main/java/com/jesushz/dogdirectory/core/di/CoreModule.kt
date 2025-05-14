package com.jesushz.dogdirectory.core.di

import com.jesushz.dogdirectory.core.data.network.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val coreModule = module {
    single {
        HttpClientFactory.build(CIO.create())
    }
}
