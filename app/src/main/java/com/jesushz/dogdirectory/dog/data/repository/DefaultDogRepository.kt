package com.jesushz.dogdirectory.dog.data.repository

import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.data.models.Dog
import com.jesushz.dogdirectory.dog.data.network.RemoteDogDataSource
import com.jesushz.dogdirectory.dog.domain.repository.DogRepository

class DefaultDogRepository(
    private val remoteDogDataSource: RemoteDogDataSource
): DogRepository {

    override suspend fun getDogs(): Result<List<Dog>, DataError> {
        return remoteDogDataSource
            .getDogs()
    }

}
