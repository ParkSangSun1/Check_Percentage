package com.pss.domain.usecase

import com.pss.domain.repository.MainRepository
import javax.inject.Inject

class GetScoreUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute() = mainRepository.getScore()
}