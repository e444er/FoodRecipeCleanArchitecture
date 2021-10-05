package com.droidios.foodrecipe.di

import com.droidios.foodrecipe.common.Constants
import com.droidios.foodrecipe.data.remate.MealSearchApi
import com.droidios.foodrecipe.data.repository.MealDetailsRepositoryImpl
import com.droidios.foodrecipe.data.repository.MealSearchRepositoryImpl
import com.droidios.foodrecipe.domain.repository.MealDetailsRepository
import com.droidios.foodrecipe.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMealSearchRepository(searchMealSearchApi: MealSearchApi) : MealSearchRepository {
        return MealSearchRepositoryImpl(searchMealSearchApi)
    }

    @Provides
    @Singleton
    fun provideMealDetailsRepository(mealSearchApi: MealSearchApi) : MealDetailsRepository {
        return MealDetailsRepositoryImpl(mealSearchApi)
    }
}