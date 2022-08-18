package com.pss.domain.usecase

import com.pss.domain.repository.SplashRepository
import javax.inject.Inject

class CheckAppVersionUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {
    suspend fun execute() = splashRepository.checkAppVersion()
}