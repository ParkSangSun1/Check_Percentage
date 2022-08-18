package com.pss.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.pss.domain.usecase.CheckAppVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAppVersionUseCase: CheckAppVersionUseCase
): ViewModel() {

   suspend fun checkAppVersion() = checkAppVersionUseCase.execute()
}
