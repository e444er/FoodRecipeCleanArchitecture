package com.droidios.foodrecipe.domain.use_case

import com.droidios.foodrecipe.common.Resource
import com.droidios.foodrecipe.data.model.toDomainMeal
import com.droidios.foodrecipe.domain.model.Meal
import com.droidios.foodrecipe.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMealsUseCase @Inject constructor(
    private val repository: MealSearchRepository,
) {
    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {

        try {
            emit(Resource.Loading())
            val data = repository.getMealList(s)
            val domainData =
                if (data.meals != null) data.meals
                    .map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }

    }
}