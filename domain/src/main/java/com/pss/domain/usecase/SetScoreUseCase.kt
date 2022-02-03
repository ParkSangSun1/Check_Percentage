package com.pss.domain.usecase

import com.pss.domain.model.DomainScore
import com.pss.domain.repository.MainRepository
import javax.inject.Inject

class SetScoreUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun execute(score: DomainScore) = mainRepository.setScore(score)
}