package com.witation.ktdemo.http

import com.witation.ktdemo.base.BRep
import kotlinx.coroutines.Dispatchers

object SpeedRep : BRep() {
    fun newsChannel() = fire(Dispatchers.IO) {
        val result = SpeedServiceImpl.newsChannels()
        when {
            result.status == 0 -> Result.success(result.result)
            else -> Result.failure(RuntimeException(result.msg))
        }
    }

    fun newsGet(channel: String, start: Int) = fire(Dispatchers.IO) {
        val params = HashMap<String, String>()
        params.put("channel", channel)
        params.put("start", start.toString())
        val result = SpeedServiceImpl.newsGet(params)
        when {
            result.status == 0 -> Result.success(result.result)
            else -> Result.failure(RuntimeException(result.msg))
        }
    }

    fun astros() = fire(Dispatchers.IO) {
        val result = SpeedServiceImpl.astros()
        when {
            result.status == 0 -> Result.success(result.result)
            else -> Result.failure(RuntimeException(result.msg))
        }
    }

    fun astroFortune(astroid: Int) = fire(Dispatchers.IO) {
        val params = HashMap<String, String>()
        params.put("astroid", astroid.toString())
        val result = SpeedServiceImpl.astroFortune(params)
        when {
            result.status == 0 -> Result.success(result.result)
            else -> Result.failure(RuntimeException(result.msg))
        }
    }
}