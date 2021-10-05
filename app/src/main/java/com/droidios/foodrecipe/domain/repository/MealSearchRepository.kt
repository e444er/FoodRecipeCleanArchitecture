package com.droidios.foodrecipe.domain.repository

import com.droidios.foodrecipe.data.model.MealsDTO

interface MealSearchRepository {

    suspend fun getMealList(s: String): MealsDTO
}