package com.jesushz.dogdirectory.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesushz.dogdirectory.core.database.dao.DogDao
import com.jesushz.dogdirectory.core.database.entity.DogEntity

@Database(
    entities = [DogEntity::class],
    version = 1
)
abstract class DogDirectoryDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao

    companion object {
        const val DATABASE_NAME = "dog_directory.db"
    }

}
