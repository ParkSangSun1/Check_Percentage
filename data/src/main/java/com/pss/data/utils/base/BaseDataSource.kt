package com.pss.data.utils.base

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.pss.domain.model.DomainScore
import com.pss.domain.model.GetFirebaseResponse
import com.pss.domain.model.SetFirebaseResponse
import com.pss.domain.utils.ErrorType
import com.pss.domain.utils.FirebaseState
import com.pss.domain.utils.RemoteErrorEmitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseDataSource {

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    /**
     * Function that executes the given function on Dispatchers.IO context and switch to Dispatchers.Main context when an error occurs
     * @param callFunction is the function that is returning the wanted object. It must be a suspend function. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     * @param emitter is the interface that handles the error messages. The error messages must be displayed on the MainThread, or else they would throw an Exception.
     */
    suspend inline fun <T> safeApiCall(emitter: RemoteErrorEmitter, crossinline callFunction: suspend () -> T): T? {
        return try{
            val myObject = withContext(Dispatchers.IO){ callFunction.invoke() }
            myObject
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when(e){
                    is HttpException -> {
                        if(e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
                        else {
                            val body = e.response()?.errorBody()
                            emitter.onError(getErrorMessage(body))
                        }
                    }
                    is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                    is IOException -> emitter.onError(ErrorType.NETWORK)
                    else -> emitter.onError(ErrorType.UNKNOWN)
                }
            }
            null
        }
    }

    /**
     * Function that executes the given function in whichever thread is given. Be aware, this is not friendly with Dispatchers.IO,
     * since [RemoteErrorEmitter] is intended to display messages to the user about error from the server/DB.
     * @param callFunction is the function that is returning the wanted object. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     * @param emitter is the interface that handles the error messages. The error messages must be displayed on the MainThread, or else they would throw an Exception.
     */
    inline fun <T> safeApiCallNoContext(emitter: RemoteErrorEmitter, callFunction: () -> T): T? {
        return try{
            val myObject = callFunction.invoke()
            myObject
        }catch (e: Exception){
            e.printStackTrace()
            Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
            when(e){
                is HttpException -> {
                    if(e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
                    else {
                        val body = e.response()?.errorBody()
                        emitter.onError(getErrorMessage(body))
                    }
                }
                is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                is IOException -> emitter.onError(ErrorType.NETWORK)
                else -> emitter.onError(ErrorType.UNKNOWN)
            }
            null
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }

    suspend inline fun <T> safeGetFirebaseRTDBCall(crossinline callFunction: () -> Task<T>) : GetFirebaseResponse<T> {
        var state = FirebaseState.FAILURE
        var result : T? = null
        callFunction.invoke()
            .addOnSuccessListener {
                result = it
                state = FirebaseState.SUCCESS
            }
            .addOnFailureListener {
                state = FirebaseState.FAILURE
            }.await()

        return GetFirebaseResponse(state = state, result = result)
    }

    suspend inline fun <T> safeSetFirebaseRTDBCall(crossinline callFunction: () -> Task<T>) : SetFirebaseResponse {
        var state = FirebaseState.FAILURE
        callFunction.invoke()
            .addOnSuccessListener {
                state = FirebaseState.SUCCESS
            }
            .addOnFailureListener {
                state = FirebaseState.FAILURE
            }.await()

        return SetFirebaseResponse(state = state)
    }

    suspend inline fun safeSetFireStoreCall(crossinline callFunction: () -> Task<QuerySnapshot>) : GetFirebaseResponse<List<DomainScore>> {
        var state = FirebaseState.FAILURE
        val result = arrayListOf<DomainScore>()

        callFunction.invoke()
            .addOnSuccessListener {
                result.clear()
                for (item in it.documents) {
                    item.toObject(DomainScore::class.java).let {
                        result.add(it!!)
                    }
                }
                state = FirebaseState.SUCCESS
            }
            .addOnFailureListener {
                state = FirebaseState.FAILURE
            }.await()

        return GetFirebaseResponse(state = state, result = result)
    }
}