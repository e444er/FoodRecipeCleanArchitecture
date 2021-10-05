package com.droidios.foodrecipe.domain.use_case

import com.droidios.foodrecipe.common.Resource
import com.droidios.foodrecipe.data.model.toDomainMealDetails
import com.droidios.foodrecipe.domain.model.MealDetails
import com.droidios.foodrecipe.domain.repository.MealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(
    private val repository: MealDetailsRepository,
) {
    operator fun invoke(id: String): Flow<Resource<List<MealDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getMealDetails(id)
            val domainData =
                if (!data.meals.isNullOrEmpty()) data.meals
                    .map { it -> it.toDomainMealDetails() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))
        }
    }
}