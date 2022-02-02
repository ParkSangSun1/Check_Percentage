package com.pss.presentation.viewmode

import androidx.lifecycle.ViewModel
import com.pss.domain.usecase.CheckAppVersionUseCase
import com.pss.domain.usecase.CheckLoveCalculatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAppVersionUseCase: CheckAppVersionUseCase
): ViewModel() {

   fun checkAppVersion() = checkAppVersionUseCase.execute()
}
