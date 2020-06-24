package com.witation.ktdemo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.witation.ktdemo.http.SpeedRep

class NewsVM : ViewModel() {
    val getNewsChannel = MutableLiveData<Boolean>()
    val newsChannels = Transformations.switchMap(getNewsChannel) {
        SpeedRep.newsChannel()
    }

    fun getNewsChannel(get: Boolean) {
        if (get) {
            getNewsChannel.value = get
        }
    }

    //当前新闻频道
    var currentChannel = ""
    //当前页数
    val page = MutableLiveData<Int>()

    fun currentPage(page: Int) {
        this.page.value = page
    }

    val newsList = Transformations.switchMap(page) {
        SpeedRep.newsGet(currentChannel, it)
    }

}