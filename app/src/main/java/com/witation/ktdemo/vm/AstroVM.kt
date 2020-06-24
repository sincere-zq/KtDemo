package com.witation.ktdemo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.witation.ktdemo.http.SpeedRep

/**
 * 星座ViewModel
 */
class AstroVM : ViewModel() {
    //所有星座数据
    val aostros = SpeedRep.astros()
    //选择的星座id
    val selectedAostroid = MutableLiveData<Int>()

    //设置选中的星座id
    fun aostroid(selectedAostroid: Int) {
        this.selectedAostroid.value = selectedAostroid
    }

    //选择的星座的星座运势
    val selectedAostroFortune = Transformations.switchMap(selectedAostroid) {
        SpeedRep.astroFortune(it)
    }
}