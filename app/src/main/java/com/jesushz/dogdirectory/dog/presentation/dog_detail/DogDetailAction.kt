package com.jesushz.dogdirectory.dog.presentation.dog_detail

sealed interface DogDetailAction {

    data object OnBackClick: DogDetailAction

}
