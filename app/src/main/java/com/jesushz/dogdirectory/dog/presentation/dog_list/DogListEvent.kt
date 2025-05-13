package com.jesushz.dogdirectory.dog.presentation.dog_list

import com.jesushz.dogdirectory.core.presentation.ui.UiText

sealed interface DogListEvent {

    data class OnError(val error: UiText): DogListEvent

}
