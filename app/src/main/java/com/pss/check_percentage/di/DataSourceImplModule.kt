package com.pss.check_percentage.di

import com.pss.data.remote.api.LoveCalculatorApi
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.repository.remote.datasourceimpl.MainDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceImplModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        loveCalculatorApi: LoveCalculatorApi
    ) : MainDataSource {
        return MainDataSourceImpl(
            loveCalculatorApi
        )
    }
}