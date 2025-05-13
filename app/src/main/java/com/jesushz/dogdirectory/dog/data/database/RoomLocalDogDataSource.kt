package com.jesushz.dogdirectory.dog.data.database

import android.database.sqlite.SQLiteFullException
import com.jesushz.dogdirectory.core.database.dao.DogDao
import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.data.mappers.toDog
import com.jesushz.dogdirectory.dog.data.mappers.toDogEntity
import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.dog.domain.local.LocalDogDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalDogDataSource(
    private val dogDao: DogDao
): LocalDogDataSource {

    override fun getDogs(): Flow<List<Dog>> {
        return dogDao
            .getAllDogs()
            .map { dogEntityList ->
                dogEntityList.map { it.toDog() }
            }
    }

    override suspend fun upsertDog(dog: Dog): Result<Unit, DataError.Local> {
        return try {
            val entity = dog.toDogEntity()
            dogDao.upsertDog(entity)
            Result.Success(Unit)
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

}
