package com.jesushz.dogdirectory.dog.data.network

import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.data.models.Dog

interface RemoteDogDataSource {
    suspend fun getDogs(): Result<List<Dog>, DataError.Remote>
}
