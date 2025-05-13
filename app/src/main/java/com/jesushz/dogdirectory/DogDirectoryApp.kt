package com.jesushz.dogdirectory

import android.app.Application
import com.jesushz.dogdirectory.core.di.coreModule
import com.jesushz.dogdirectory.dog.di.dogModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class DogDirectoryApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@DogDirectoryApp)
            modules(
                coreModule,
                dogModule
            )
        }
    }

}