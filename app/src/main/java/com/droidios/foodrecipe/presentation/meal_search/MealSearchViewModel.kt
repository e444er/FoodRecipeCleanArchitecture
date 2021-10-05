package com.droidios.foodrecipe.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidios.foodrecipe.common.Resource
import com.droidios.foodrecipe.domain.use_case.SearchMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val getMealSearchListUseCase: SearchMealsUseCase,
) : ViewModel() {

    private val _mealSearchState = MutableStateFlow(MealSearchState())
    val mealSearchState: StateFlow<MealSearchState> = _mealSearchState

    fun searchMealList(s: String) {
        getMealSearchListUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchState.value = MealSearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealSearchState.value = MealSearchState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealSearchState.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}