package com.droidios.foodrecipe.presentation.meal_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidios.foodrecipe.common.Resource
import com.droidios.foodrecipe.domain.use_case.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
) : ViewModel() {

    private val _mealDetailsState = MutableStateFlow(MealDetailsState())
    val mealDetailsState: StateFlow<MealDetailsState> = _mealDetailsState

    fun getDetailsMealList(id: String) {
        getMealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetailsState.value = MealDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetailsState.value = MealDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetailsState.value = MealDetailsState(data = it.data?.get(0))
                }
            }
        }.launchIn(viewModelScope)
    }
}