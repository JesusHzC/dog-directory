package com.jesushz.dogdirectory.dog.data.repository

import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.core.domain.onError
import com.jesushz.dogdirectory.core.domain.onSuccess
import com.jesushz.dogdirectory.dog.data.models.Dog
import com.jesushz.dogdirectory.dog.domain.local.LocalDogDataSource
import com.jesushz.dogdirectory.dog.domain.network.RemoteDogDataSource
import com.jesushz.dogdirectory.dog.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

class DefaultDogRepository(
    private val remoteDogDataSource: RemoteDogDataSource,
    private val localDogDataSource: LocalDogDataSource
): DogRepository {

    override suspend fun getDogs(): Result<List<Dog>, DataError> {
        val localDogs = localDogDataSource.getDogs().first()
        if (localDogs.isNotEmpty()) {
            return Result.Success(localDogs)
        }

        return when (val result = remoteDogDataSource.getDogs()) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                result.data.forEach { dog ->
                    val insertResult = localDogDataSource.upsertDog(dog)
                    if (insertResult is Result.Error) {
                        return Result.Error(insertResult.error)
                    }
                }
                Result.Success(result.data)
            }
        }
    }

}
