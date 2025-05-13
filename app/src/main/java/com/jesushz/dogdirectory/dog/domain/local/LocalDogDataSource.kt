package com.jesushz.dogdirectory.dog.domain.local

import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.data.models.Dog
import kotlinx.coroutines.flow.Flow

interface LocalDogDataSource {

    fun getDogs(): Flow<List<Dog>>
    suspend fun upsertDog(dog: Dog): Result<Unit, DataError.Local>

}