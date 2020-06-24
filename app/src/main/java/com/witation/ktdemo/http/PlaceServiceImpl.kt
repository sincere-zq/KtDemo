package com.witation.ktdemo.http

import com.witation.ktdemo.base.BService
import com.witation.ktdemo.base.ServiceCreator

object PlaceServiceImpl : BService() {

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val placeService = ServiceCreator.create(BASE_URL, PlaceService::class.java)

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

}