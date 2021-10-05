package com.droidios.foodrecipe.data.repository

import com.droidios.foodrecipe.data.model.MealsDTO
import com.droidios.foodrecipe.data.remate.MealSearchApi
import com.droidios.foodrecipe.domain.repository.MealDetailsRepository

class MealDetailsRepositoryImpl (
    private val api: MealSearchApi,
) : MealDetailsRepository {
    override suspend fun getMealDetails(id: String): MealsDTO {
        return api.getMealDetails(id)
    }
}