package com.pss.domain.usecase

import com.pss.domain.repository.MainRepository
import com.pss.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class CheckLoveCalculatorUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun execute(remoteErrorEmitter: RemoteErrorEmitter, host : String, key : String, mName : String, wName : String) = mainRepository.checkLoveCalculator(remoteErrorEmitter, host, key, mName, wName)
}