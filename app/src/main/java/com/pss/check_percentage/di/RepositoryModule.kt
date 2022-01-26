package com.pss.check_percentage.di

import com.pss.data.repository.MainRepositoryImpl
import com.pss.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.pss.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(
        mainDataSourceImpl: MainDataSourceImpl
    ): MainRepository {
        return MainRepositoryImpl(
            mainDataSourceImpl
        )
    }
}