package com.witation.ktdemo.http

import com.witation.ktdemo.model.BResp
import com.witation.ktdemo.model.Place
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    companion object {
        const val TOKEN = "AtmW5rINE92XITTh"
    }

    @GET("v2/place?token=${TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<BResp<List<Place>>>
}