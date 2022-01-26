package com.pss.presentation.viewmode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.domain.usecase.CheckLoveCalculatorUseCase
import com.pss.domain.utils.ErrorType
import com.pss.domain.utils.RemoteErrorEmitter
import com.pss.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase
) : ViewModel(), RemoteErrorEmitter {

    val errorType: LiveData<ErrorType> get() = _errorType
    private var _errorType = SingleLiveEvent<ErrorType>()

    val errorMessage: LiveData<String> get() = _errorMessage
    private var _errorMessage = SingleLiveEvent<String>()

    var manNameResult = "manEx"
    var womanNameResult = "womanEx"

    fun checkLoveCalculator(host : String, key : String, mName : String, wName : String) = viewModelScope.launch {
        checkLoveCalculatorUseCase.execute(this@MainViewModel, host, key, mName, wName).let { response ->
            Log.d("로그","response : $response")
        }
    }

    override fun onError(msg: String) {
        Log.d("로그","error msg : $msg")
        _errorMessage.postValue(msg)
    }

    override fun onError(errorType: ErrorType) {
        Log.d("로그","error type : $errorType")
        _errorType.postValue(errorType)
    }
}