package com.lbnkosi.weatherapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class RemoteRepository {

    suspend inline fun <T> apiCall(crossinline callFunction: suspend () -> T): Flow<Result<T>> {
        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            flow { emit(Result.success(myObject)) }
        } catch (e: Exception) {
            flow { emit(Result.failure(e)) }
        }
    }

}