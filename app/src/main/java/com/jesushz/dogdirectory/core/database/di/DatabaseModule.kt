package com.jesushz.dogdirectory.core.database.di

import androidx.room.Room
import com.jesushz.dogdirectory.core.database.DogDirectoryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            DogDirectoryDatabase::class.java,
            DogDirectoryDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(false).build()
    }

    single { get<DogDirectoryDatabase>().dogDao() }

}
