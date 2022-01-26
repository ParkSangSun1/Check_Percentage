package com.pss.check_percentage.di

import com.pss.domain.repository.MainRepository
import com.pss.domain.usecase.CheckLoveCalculatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideCheckLoveCalculatorUseCase(repository: MainRepository) = CheckLoveCalculatorUseCase(repository)

}