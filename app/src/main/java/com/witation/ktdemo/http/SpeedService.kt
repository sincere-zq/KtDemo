package com.witation.ktdemo.http

import com.witation.ktdemo.model.Astro
import com.witation.ktdemo.model.AstroFortunneResult
import com.witation.ktdemo.model.NewsResult
import com.witation.ktdemo.model.SpeedResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface SpeedService {
    companion object {
        const val APP_KEY = "9cde3a9af5f7b359"
        const val NEWS_CHANNEL = "news/channel?appkey=${APP_KEY}"
        const val NEWS_GET = "news/get?appkey=${APP_KEY}"
        const val ASTRO_GET = "astro/all?appkey=${APP_KEY}"
        const val ASTRO_FORTUNE = "astro/fortune?appkey=${APP_KEY}"
    }

    @GET(NEWS_CHANNEL)
    fun newsChannels(): Call<SpeedResp<List<String>>>

    @POST(NEWS_GET)
    fun newsGet(@QueryMap params: Map<String, String>): Call<SpeedResp<NewsResult>>

    @GET(ASTRO_GET)
    fun astros(): Call<SpeedResp<List<Astro>>>

    @POST(ASTRO_FORTUNE)
    fun astroFortune(@QueryMap params: Map<String, String>): Call<SpeedResp<AstroFortunneResult>>
}