package com.witation.ktdemo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.witation.ktdemo.http.PlaceRep

class MainVM : ViewModel() {
    private val searchContent = MutableLiveData<String>()

    fun inputedContent(content: String) {
        searchContent.value = content;
    }

    val datas = Transformations.switchMap(searchContent) {
        PlaceRep.searchPlaces(it)
    }
}