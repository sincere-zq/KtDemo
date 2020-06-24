package com.witation.ktdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Fragment基类
 */
abstract class BFrag<VB : ViewBinding> : Fragment() {
    protected lateinit var vb: VB
    private var hasLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = viewbind()
        return vb.root
    }

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!hasLoaded && !isHidden) {
            initViews()
            initVm()
            initDatas()
            hasLoaded = true
        }
    }


    protected abstract fun viewbind(): VB

    protected abstract fun initViews()

    protected open fun initVm() {
    }

    protected abstract fun initDatas()

    override fun onDestroyView() {
        super.onDestroyView()
        hasLoaded = false;
    }
}