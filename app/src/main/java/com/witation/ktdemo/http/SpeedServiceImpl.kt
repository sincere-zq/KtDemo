package com.witation.ktdemo.http

import com.witation.ktdemo.base.BService
import com.witation.ktdemo.base.ServiceCreator

object SpeedServiceImpl : BService() {
    private const val BASE_URL = "https://api.jisuapi.com/"

    private val speedService = ServiceCreator.create(BASE_URL, SpeedService::class.java)

    suspend fun newsChannels() = speedService.newsChannels().await()

    suspend fun newsGet(params: Map<String, String>) = speedService.newsGet(params).await()

    suspend fun astros() = speedService.astros().await()

    suspend fun astroFortune(params: Map<String, String>) =
        speedService.astroFortune(params).await()
}