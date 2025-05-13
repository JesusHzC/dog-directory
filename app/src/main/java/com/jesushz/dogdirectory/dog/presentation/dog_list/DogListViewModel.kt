package com.jesushz.dogdirectory.dog.presentation.dog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesushz.dogdirectory.core.domain.onError
import com.jesushz.dogdirectory.core.domain.onSuccess
import com.jesushz.dogdirectory.dog.data.models.Dog
import com.jesushz.dogdirectory.dog.domain.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class DogListViewModel(
    private val repository: DogRepository
): ViewModel() {

    private var cachedDogs: List<Dog> = emptyList()

    private val _state = MutableStateFlow(DogListState())
    val state = _state
        .onStart {
            if (cachedDogs.isEmpty()) {
                fetchDogs()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = _state.value
        )

    private fun fetchDogs() {
        viewModelScope.launch {
            repository
                .getDogs()
                .onSuccess { result ->
                    cachedDogs = result
                    _state.update {
                        it.copy(
                            dogs = result,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    Timber.e("$error")
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }

}
