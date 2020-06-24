package com.witation.ktdemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * 带ViewModel
 */
abstract class BVMFrag<VB : ViewBinding, VM : ViewModel> : BFrag<VB>() {
    protected lateinit var vm: VM

    override fun initVm() {
        super.initVm()
        vm = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(viewmodel())
    }

    protected abstract fun viewmodel(): Class<VM>
}