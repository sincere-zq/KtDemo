package com.witation.ktdemo.model

import android.location.Location
import com.google.gson.annotations.SerializedName

data class BResp<T>(val status: String, val places: T)

data class Place(
    val name: String,
    val location: Location, @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)