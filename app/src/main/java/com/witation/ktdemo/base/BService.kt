package com.witation.ktdemo.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

open class BService {
    companion object {
        const val NET_ERROR = "网络已断开"
        const val CONNECT_FAILURE = "连接失败"
        const val REQUEST_TIME_OUT = "请求超时"
        const val RESP_IS_NULL = "返回结果为空"
    }

    protected suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    when {
                        t is UnknownHostException -> it.resumeWithException(
                            RuntimeException(
                                NET_ERROR
                            )
                        )
                        t is ConnectException -> it.resumeWithException(
                            RuntimeException(
                                CONNECT_FAILURE
                            )
                        )
                        t is HttpException -> it.resumeWithException(
                            RuntimeException(
                                REQUEST_TIME_OUT
                            )
                        )
                        t is SocketTimeoutException -> it.resumeWithException(
                            RuntimeException(
                                REQUEST_TIME_OUT
                            )
                        )
                        else -> it.resumeWithException(t)
                    }
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    when {
                        body == null -> it.resumeWithException(RuntimeException(RESP_IS_NULL))
                        else -> it.resume(body)
                    }
                }
            })
        }
    }
}