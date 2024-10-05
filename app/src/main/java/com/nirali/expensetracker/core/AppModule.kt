package com.nirali.expensetracker.core

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.nirali.expensetracker.core.data.repository.ExpenseCategoryRepositoryImpl
import com.nirali.expensetracker.core.data.room.dao.ExpenseDao
import com.nirali.expensetracker.core.data.room.database.ExpenseTrackerAppDatabase
import com.nirali.expensetracker.core.domain.repository.ExpenseCategoryRepository
import com.nirali.restApi.data.remote.ApiService
import com.nirali.restApi.data.remote.RetrofitInstance
import com.nirali.restApi.data.repository.NewsRepositoryImpl
import com.nirali.restApi.data.room.NewsDao
import com.nirali.restApi.data.room.NewsDatabase
import com.nirali.restApi.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExpenseDao(expenseTrackerAppDatabase: ExpenseTrackerAppDatabase): ExpenseDao {
        return expenseTrackerAppDatabase.expenseDao
    }

    @Provides
    @Singleton
    fun provideExpenseTrackerRepository(expenseDao: ExpenseDao): ExpenseCategoryRepository {
        return ExpenseCategoryRepositoryImpl(expenseDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ExpenseTrackerAppDatabase {
        return Room.databaseBuilder(
            app,
            ExpenseTrackerAppDatabase::class.java,
            "expense_tracker_db"
        ).build()


    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(apiService,newsDao)
    }


    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}