package com.codegalaxy.practicewithbhanu.di

import com.codegalaxy.practicewithbhanu.model.ApiService
import com.codegalaxy.practicewithbhanu.model.DataDao
import com.codegalaxy.practicewithbhanu.model.DataRepository
import com.codegalaxy.practicewithbhanu.model.IDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataDao(database: AppDatabase): DataDao {
        return database.dataDao()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService,dao: DataDao) : IDataRepository {
        return DataRepository(apiService,dao)
    }
}
