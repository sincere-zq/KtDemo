package com.witation.ktdemo.http

import com.witation.ktdemo.base.BRep
import kotlinx.coroutines.Dispatchers

object PlaceRep : BRep() {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val resp = PlaceServiceImpl.searchPlaces(query)
        if (resp.status == "ok") {
            Result.success(resp.places)
        } else {
            Result.failure(RuntimeException("Response status is ${resp.status}"))
        }
    }
}