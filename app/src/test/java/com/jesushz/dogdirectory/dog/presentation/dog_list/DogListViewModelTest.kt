package com.jesushz.dogdirectory.dog.presentation.dog_list

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.jesushz.dogdirectory.MainCoroutineExtension
import com.jesushz.dogdirectory.TestMockEngine
import com.jesushz.dogdirectory.core.data.network.HttpClientFactory
import com.jesushz.dogdirectory.dog.data.network.KtorRemoteDogDataSource
import com.jesushz.dogdirectory.dog.data.repository.DefaultDogRepository
import com.jesushz.dogdirectory.dogListResponseRemoteStub
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.headers
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class DogListViewModelTest {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()
    }

    private lateinit var mockEngine: TestMockEngine
    private lateinit var remoteDogDataSource: KtorRemoteDogDataSource
    private lateinit var localDogDataSource: FakeRoomLocalDogDataSource
    private lateinit var dogRepository: DefaultDogRepository
    private lateinit var viewModel: DogListViewModel

    @BeforeEach
    fun setUp() {
        // Setup Ktor
        val mockEngineConfig = MockEngineConfig().apply {
            requestHandlers.add { request ->
                val relativeUrl = request.url
                if(relativeUrl == Url(urlString = "https://jsonblob.com/api/1151549092634943488")) {
                    respond(
                        content = ByteReadChannel(
                            text = Json.encodeToString(dogListResponseRemoteStub)
                        ),
                        headers = headers {
                            set("Content-Type", "application/json")
                        }
                    )
                } else {
                    respond(
                        content = byteArrayOf(),
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }
        }
        mockEngine = TestMockEngine(
            dispatcher = mainCoroutineExtension.testDispatcher,
            mockEngineConfig = mockEngineConfig
        )

        val httpClient = HttpClientFactory.build(mockEngine)
        remoteDogDataSource = KtorRemoteDogDataSource(
            httpClient = httpClient
        )

        localDogDataSource = FakeRoomLocalDogDataSource()

        dogRepository = DefaultDogRepository(
            remoteDogDataSource = remoteDogDataSource,
            localDogDataSource = localDogDataSource
        )

        viewModel = DogListViewModel(
            repository = dogRepository
        )
    }

    @Test
    fun `test fetch dog list from remote and save data in database`() = runTest {
        viewModel.state.test {
            val emission1 = awaitItem()
            assertThat(emission1.isLoading).isTrue()
            assertThat(emission1.dogs).isEqualTo(emptyList())

            val emission2 = awaitItem()
            assertThat(emission2.isLoading).isFalse()
            assertThat(emission2.dogs).isEqualTo(dogListResponseRemoteStub)

            // Verify if remote data is equals to local data
            localDogDataSource.getDogs().test {
                assertThat(awaitItem()).isEqualTo(dogListResponseRemoteStub)
            }
        }
    }

}