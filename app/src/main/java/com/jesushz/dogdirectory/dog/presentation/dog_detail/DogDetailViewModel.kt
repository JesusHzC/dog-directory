package com.jesushz.dogdirectory.dog.presentation.dog_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.jesushz.dogdirectory.core.data.models.Dog
import com.jesushz.dogdirectory.core.util.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DogDetailViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DogDetailState())
    val state = _state.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<Routes.DogDetailScreen>()
        _state.update {
            it.copy(
                dog = Dog(
                    name = route.name,
                    description = route.description,
                    age = route.age,
                    image = route.image
                )
            )
        }
    }

}
