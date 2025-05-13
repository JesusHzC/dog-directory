package com.jesushz.dogdirectory.dog.presentation.dog_list

sealed interface DogListAction {

    data object OnBackClick: DogListAction

}
