package com.pss.domain.usecase

import com.pss.domain.repository.MainRepository
import javax.inject.Inject

class SetStatisticsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute(plusResult: Int) = mainRepository.setStatistics(plusResult)
}