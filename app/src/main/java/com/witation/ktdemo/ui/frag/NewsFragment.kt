package com.witation.ktdemo.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.witation.ktdemo.base.BVMFrag
import com.witation.ktdemo.databinding.FragmentNewsBinding
import com.witation.ktdemo.ui.adapter.NewsChannelAdapter
import com.witation.ktdemo.utils.TabLayoutAnimUtils
import com.witation.ktdemo.utils.showFailure
import com.witation.ktdemo.vm.NewsVM
import java.util.*


/**
 * 新闻
 */
class NewsFragment : BVMFrag<FragmentNewsBinding, NewsVM>(), TabLayout.OnTabSelectedListener {

    override fun viewbind(): FragmentNewsBinding =
        FragmentNewsBinding.inflate(LayoutInflater.from(activity))

    override fun viewmodel() = NewsVM::class.java
    override fun initViews() {
        vb.tabLayout.setupWithViewPager(vb.vpContent)
        //设置自定义动画
        vb.tabLayout.addOnTabSelectedListener(this)
        vb.loadView.setOnClickListener {
            vb.loadView.loading()
            vm.getNewsChannel(true)
        }
    }

    override fun initDatas() {
        vm.newsChannels.observe(this, Observer {
            val titles = it.getOrNull()
            val fragList = ArrayList<androidx.fragment.app.Fragment>()
            if (titles != null) {
                for (title in titles) {
                    val frag = NewsListFragment()
                    val bundle = Bundle()
                    bundle.putString(NewsListFragment.CHANNEL, title)
                    frag.arguments = bundle
                    fragList.add(frag)
                }
                vb.vpContent.adapter = NewsChannelAdapter(childFragmentManager, titles, fragList)
                vb.loadView.visibility = View.GONE
            } else {
                showFailure(it.exceptionOrNull()!!.message!!, vb.loadView)
            }
        })
        vm.getNewsChannel(true)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TabLayoutAnimUtils.changeTabNormal(tab)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        TabLayoutAnimUtils.changeTabSelect(tab)
    }

}
