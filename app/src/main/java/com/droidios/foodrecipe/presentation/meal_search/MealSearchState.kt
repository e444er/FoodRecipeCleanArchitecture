package com.droidios.foodrecipe.presentation.meal_search

import com.droidios.foodrecipe.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
//sealed class Resull {
//    object Loading:Resull()
//    data class Success(val search: List<Search>): Resull()
//    data class Error(val msg:Throwable): Resull()
//    object Empty:Resull()
//}