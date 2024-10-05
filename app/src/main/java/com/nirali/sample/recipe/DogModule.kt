package com.nirali.sample.recipe

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nirali.sample.recipe.data.remote.ApiService
import com.nirali.sample.recipe.data.remote.RetrofitInstance
import com.nirali.sample.recipe.data.repository.RecipeRepositoryImpl
import com.nirali.sample.recipe.data.room.DogDao
import com.nirali.sample.recipe.data.room.DogDatabase
import com.nirali.sample.recipe.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DogModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideDogRepository(apiService: ApiService): RecipeRepository {
        return RecipeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDogDatabase(app: Application): DogDatabase {
        return Room.databaseBuilder(
            app,
            DogDatabase::class.java,
            "dog_db"
        ).build()

    }


    @Provides
    @Singleton
    fun provideDogDao(database: DogDatabase): DogDao {
        return database.dogDao
    }


}