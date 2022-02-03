package com.pss.domain.usecase

import com.pss.domain.repository.MainRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute() = mainRepository.getStatistics()
}