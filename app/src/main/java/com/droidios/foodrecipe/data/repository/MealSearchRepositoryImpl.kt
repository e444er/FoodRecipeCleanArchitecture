package com.droidios.foodrecipe.data.repository

import com.droidios.foodrecipe.data.model.MealsDTO
import com.droidios.foodrecipe.data.remate.MealSearchApi
import com.droidios.foodrecipe.domain.repository.MealSearchRepository

class MealSearchRepositoryImpl(
    private val api: MealSearchApi,
) : MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return api.getMealList(s)
    }
}