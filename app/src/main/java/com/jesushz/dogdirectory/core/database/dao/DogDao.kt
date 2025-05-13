package com.jesushz.dogdirectory.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jesushz.dogdirectory.core.database.entity.DogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {

    @Upsert
    suspend fun upsertDog(dog: DogEntity)

    @Query("SELECT * FROM dogentity")
    fun getAllDogs(): Flow<List<DogEntity>>

}
