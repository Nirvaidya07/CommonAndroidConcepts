/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :12:32 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirali.dogBreed.domain.model.BreedResponse
import com.nirali.sample.recipe.components.Resource
import com.nirali.sample.recipe.data.room.DogDao
import com.nirali.sample.recipe.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class DogState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val message: String = "",
    val breedList: BreedResponse? = null
)

@HiltViewModel
class DogViewmodel @Inject constructor(
    private val dogRepository: RecipeRepository,
    val dao: DogDao
) : ViewModel() {
    private val _dogState = MutableStateFlow(DogState())
    val dogState: StateFlow<DogState> = _dogState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            getBreeds()

        }
    }

    fun getBreeds() {
        viewModelScope.launch {
            _isRefreshing.update { false }
            _dogState.value = DogState(isLoading = true)

            val localData = dao.getAllBeads()
            if (localData.isNotEmpty()) {
                _dogState.value = DogState(
                    breedList = BreedResponse(localData),
                    isLoading = false
                )

                return@launch
            }

            val result = dogRepository.getRecipes()
            when (result) {

                is Resource.Success -> {
                    _dogState.value = DogState(
                        breedList = result.data,
                        isLoading = false
                    )

                    result.data?.data?.let { dao.insertBread(it) }
                }

                is Resource.Error -> {
                    _dogState.value = DogState(
                        message = result.message.toString(),
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _dogState.value = DogState(isLoading = true)

                }
            }
        }

    }
}