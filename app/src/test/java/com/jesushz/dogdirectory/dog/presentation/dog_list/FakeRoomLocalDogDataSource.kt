package com.jesushz.dogdirectory.dog.presentation.dog_list

import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.core.domain.DataError
import com.jesushz.dogdirectory.core.domain.Result
import com.jesushz.dogdirectory.dog.domain.local.LocalDogDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRoomLocalDogDataSource: LocalDogDataSource {

    private val dogs = mutableListOf<Dog>()
    private val dogFlow = MutableStateFlow<List<Dog>>(emptyList())

    override fun getDogs(): Flow<List<Dog>> = dogFlow

    override suspend fun upsertDog(dog: Dog): Result<Unit, DataError.Local> {
        val index = dogs.indexOfFirst { it.id == dog.id }
        if (index >= 0) {
            dogs[index] = dog
        } else {
            dogs.add(dog)
        }
        dogFlow.value = dogs.toList()
        return Result.Success(Unit)
    }

}
