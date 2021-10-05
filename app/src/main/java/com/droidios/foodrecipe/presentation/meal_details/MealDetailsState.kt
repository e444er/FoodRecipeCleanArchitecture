package com.droidios.foodrecipe.presentation.meal_details

import com.droidios.foodrecipe.domain.model.MealDetails

data class MealDetailsState(
    val data: MealDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
)
