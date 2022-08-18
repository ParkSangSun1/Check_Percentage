package com.pss.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.domain.model.DomainLoveResponse
import com.pss.domain.model.DomainScore
import com.pss.domain.usecase.*
import com.pss.domain.utils.ErrorType
import com.pss.domain.utils.FirebaseState
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
    private val setScoreUseCase: SetScoreUseCase,
    private val getScoreUseCase: GetScoreUseCase
) : ViewModel(), RemoteErrorEmitter {

    val apiCallEvent: LiveData<ScreenState> get() = _apiCallEvent
    private var _apiCallEvent = SingleLiveEvent<ScreenState>()

    val getStatisticsDisplayEvent: LiveData<Int> get() = _getStatisticsDisplayEvent
    private var _getStatisticsDisplayEvent = SingleLiveEvent<Int>()

    val getScoreEvent: LiveData<Any> get() = _getScoreEvent
    private var _getScoreEvent = SingleLiveEvent<Any>()

    var apiCallResult = DomainLoveResponse("", "", 0, "")
    var apiErrorType = ErrorType.UNKNOWN
    var errorMessage = "none"
    var manNameResult = "manEx"
    var womanNameResult = "womanEx"
    var scoreList = arrayListOf<DomainScore>()


    fun checkLoveCalculator(host: String, key: String, mName: String, wName: String) =
        viewModelScope.launch {
            checkLoveCalculatorUseCase.execute(this@MainViewModel, host, key, mName, wName)
                .let { response ->
                    if (response != null) {
                        apiCallResult = response
                        _apiCallEvent.postValue(ScreenState.LOADING)
                    } else _apiCallEvent.postValue(ScreenState.ERROR)
                }
        }

    suspend fun getStatistics() = getStatisticsUseCase.execute()

    suspend fun setStatistics(plusResult: Int) = setStatisticsUseCase.execute(plusResult)

    fun getStatisticsDisplay() = viewModelScope.launch {
        with(getStatisticsUseCase.execute()){
            if(this.state == FirebaseState.SUCCESS){
                _getStatisticsDisplayEvent.value = this.result.toString().toInt()
            }
        }
    }

    fun getScore() = viewModelScope.launch {
        with(getScoreUseCase.execute()){
            if (this.state == FirebaseState.SUCCESS){
                scoreList.clear()
                for (item in this.result!!) {
                    scoreList.add(item)
                }
                _getScoreEvent.call()
            }
        }
    }

    fun setScore(man: String, woman: String, percentage: Int, date: String) = viewModelScope.launch {
        setScoreUseCase.execute(DomainScore(man, woman, percentage, date))
    }

    override fun onError(msg: String) {
        errorMessage = msg
    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }
}