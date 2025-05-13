package com.jesushz.dogdirectory.dog.presentation.dog_list

import com.jesushz.dogdirectory.dog.data.models.Dog

data class DogListState(
    val dogs: List<Dog> = emptyList(),
    val isLoading: Boolean = true,
)
