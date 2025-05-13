package com.jesushz.dogdirectory.dog.data.network

import com.jesushz.dogdirectory.core.data.network.safeCall
import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.data.models.Dog
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorRemoteDogDataSource(
    private val httpClient: HttpClient
): RemoteDogDataSource {

    override suspend fun getDogs(): Result<List<Dog>, DataError.Remote> {
        return safeCall<List<Dog>> {
            httpClient.get(
                urlString = BASE_URL
            )
        }
    }

    companion object {
        private const val BASE_URL = "https://jsonblob.com/api/1151549092634943488"
    }
}
