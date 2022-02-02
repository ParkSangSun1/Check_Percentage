package com.pss.check_percentage.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.data.remote.api.LoveCalculatorApi
import com.pss.data.repository.remote.datasource.MainDataSource
import com.pss.data.repository.remote.datasource.SplashDataSource
import com.pss.data.repository.remote.datasourceimpl.MainDataSourceImpl
import com.pss.data.repository.remote.datasourceimpl.SplashDataSourceImpl
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

    @Provides
    @Singleton
    fun provideSplashDataSource(
        firebaseRtdb : FirebaseDatabase,
        fireStore : FirebaseFirestore
    ) : SplashDataSource {
        return SplashDataSourceImpl(
            firebaseRtdb = firebaseRtdb, fireStore = fireStore
        )
    }
}