package com.pss.presentation.viewmode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.model.DomainScore
import com.pss.domain.usecase.CheckLoveCalculatorUseCase
import com.pss.domain.usecase.GetStatisticsUseCase
import com.pss.domain.usecase.SetScoreUseCase
import com.pss.domain.usecase.SetStatisticsUseCase
import com.pss.domain.utils.ErrorType
import com.pss.domain.utils.RemoteErrorEmitter
import com.pss.domain.utils.ScreenState
import com.pss.presentation.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkLoveCalculatorUseCase: CheckLoveCalculatorUseCase,
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val setStatisticsUseCase: SetStatisticsUseCase,
    private val setScoreUseCase: SetScoreUseCase
) : ViewModel(), RemoteErrorEmitter {

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private var _apiCallEvent = SingleLiveEvent<ScreenState>()

    val getStatisticsDisplayEvent: LiveData<Int> get() = _getStatisticsDisplayEvent
    private var _getStatisticsDisplayEvent = SingleLiveEvent<Int>()

    var apiCallResult = DomainLoveResponse("","",0,"")
    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage ="none"
    var manNameResult = "manEx"
    var womanNameResult = "womanEx"


    fun checkLoveCalculator(host: String, key: String, mName: String, wName: String) =
        viewModelScope.launch {
            checkLoveCalculatorUseCase.execute(this@MainViewModel, host, key, mName, wName)
                .let { response ->
                    if (response != null) {
                        apiCallResult = response
                        _apiCallEvent.postValue(ScreenState.LOADING)
                    }
                    else _apiCallEvent.postValue(ScreenState.ERROR)
                }
        }

    fun getStatistics() = getStatisticsUseCase.execute()

    fun getStatisticsDisplay() = getStatisticsUseCase.execute()
        .addOnSuccessListener {
            _getStatisticsDisplayEvent.value = it.value.toString().toInt()
        }

    fun setStatistics(plusResult : Int) = setStatisticsUseCase.execute(plusResult)

    fun setScore(man : String, woman : String,percentage : Int ,date : String){
        setScoreUseCase.execute(DomainScore(man, woman, percentage, date))
    }

    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}