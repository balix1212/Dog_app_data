package com.verticalcoding.dogs.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verticalcoding.dogs.data.DogRepository
import com.verticalcoding.dogs.data.models.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    val loading = MutableLiveData(false)

    fun getDogDetails(id: Int): Flow<Dog?> {
        return repository.dogs.map { dogs -> dogs.firstOrNull { it.id == id } }
    }

    fun refreshDogs() {
        viewModelScope.launch {
            loading.value = true
            try {
                repository.fetchDogsFromApi()
            } finally {
                loading.value = false
            }
        }
    }
}

