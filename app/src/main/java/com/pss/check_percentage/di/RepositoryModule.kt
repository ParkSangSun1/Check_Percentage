package com.pss.check_percentage.di

import com.pss.data.repository.MainRepositoryImpl
import com.pss.data.repository.SplashRepositoryImpl
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
        mainDataSourceImpl: MainDataSourceImpl
    ): MainRepository {
        return MainRepositoryImpl(
            mainDataSourceImpl
        )
    }

    @Provides
    @Singleton
    fun provideSplashRepository(
        splashDataSourceImpl: SplashDataSourceImpl
    ): SplashRepository {
        return SplashRepositoryImpl(
            splashDataSourceImpl
        )
    }
}