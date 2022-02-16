package com.pss.check_percentage.di

import com.pss.data.repository.MainRepositoryImpl
import com.pss.data.repository.SplashRepositoryImpl
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.repository.remote.datasource.SplashDataSource
import com.pss.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.pss.data.repository.remote.datasourceimpl.SplashDataSourceImpl
import com.pss.domain.repository.MainRepository
import com.pss.domain.repository.SplashRepository
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
        mainDataSource : MainDataSource
    ): MainRepository {
        return MainRepositoryImpl(
            mainDataSource
        )
    }

    @Provides
    @Singleton
    fun provideSplashRepository(
        splashDataSource: SplashDataSource
    ): SplashRepository {
        return SplashRepositoryImpl(
            splashDataSource
        )
    }
}