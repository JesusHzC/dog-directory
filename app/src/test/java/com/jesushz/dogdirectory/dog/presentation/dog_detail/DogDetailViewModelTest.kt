package com.jesushz.dogdirectory.dog.presentation.dog_detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.core.util.Routes
import com.jesushz.dogdirectory.dogListResponseRemoteStub
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DogDetailViewModelTest {

    private lateinit var saveStateHandle: SavedStateHandle
    private lateinit var viewModel: DogDetailViewModel
    private lateinit var dogSelected: Dog

    @BeforeEach
    fun setUp() {
        dogSelected = dogListResponseRemoteStub.first()

        saveStateHandle = SavedStateHandle()
        val routeExpected = Routes.DogDetailScreen(
            name = dogSelected.name,
            description = dogSelected.description,
            age = dogSelected.age,
            image = dogSelected.image,
        )

        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { saveStateHandle.toRoute<Routes.DogDetailScreen>() } returns routeExpected

        viewModel = DogDetailViewModel(saveStateHandle)
    }

    @Test
    fun `test dog selected is not null and is the same that received from navigation`() = runTest {
        viewModel.state.test {
            val emission1 = awaitItem()
            assertThat(emission1.dog).isNotNull()
            assertThat(emission1.dog?.name).isEqualTo(dogSelected.name)
            assertThat(emission1.dog?.description).isEqualTo(dogSelected.description)
            assertThat(emission1.dog?.age).isEqualTo(dogSelected.age)
            assertThat(emission1.dog?.image).isEqualTo(dogSelected.image)
        }
    }
}
