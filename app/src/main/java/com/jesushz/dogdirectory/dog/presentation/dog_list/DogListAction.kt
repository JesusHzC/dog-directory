package com.jesushz.dogdirectory.dog.presentation.dog_list

import com.jesushz.dogdirectory.core.data.models.Dog

sealed interface DogListAction {

    data object OnBackClick: DogListAction
    data class OnDogClick(val dog: Dog): DogListAction

}
