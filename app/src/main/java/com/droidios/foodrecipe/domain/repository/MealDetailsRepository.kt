package com.droidios.foodrecipe.domain.repository

import com.droidios.foodrecipe.data.model.MealsDTO

interface MealDetailsRepository {
    suspend fun getMealDetails(id: String): MealsDTO
}