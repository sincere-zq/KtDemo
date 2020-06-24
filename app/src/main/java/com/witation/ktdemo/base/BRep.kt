package com.witation.ktdemo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlin.coroutines.CoroutineContext

open class BRep {
    protected fun <T> fire(
        context: CoroutineContext,
        block: suspend () -> Result<T>
    ): LiveData<Result<T>> {
        return liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
    }
}