package com.witation.ktdemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * 带ViewModel
 */
abstract class BVMAct<VB : ViewBinding, VM : ViewModel> : BAct<VB>() {
    protected lateinit var vm: VM
    override fun initVm() {
        super.initVm()
        vm = ViewModelProvider.AndroidViewModelFactory(application).create(viewmodel())
    }

    protected abstract fun viewmodel(): Class<VM>

}