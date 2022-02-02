package com.pss.check_percentage.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseRTDB() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}