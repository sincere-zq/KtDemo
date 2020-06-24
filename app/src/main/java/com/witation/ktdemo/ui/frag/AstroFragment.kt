package com.witation.ktdemo.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BVMFrag
import com.witation.ktdemo.databinding.FragmentAstroBinding
import com.witation.ktdemo.ui.act.AstroFortuneActivity
import com.witation.ktdemo.ui.adapter.AstroAdapter
import com.witation.ktdemo.utils.IntentUtils
import com.witation.ktdemo.utils.showFailure
import com.witation.ktdemo.vm.AstroVM

/**
 * 星座
 */
class AstroFragment : BVMFrag<FragmentAstroBinding, AstroVM>(), OnItemClickListener {

    private lateinit var adapter: AstroAdapter
    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun viewbind(): FragmentAstroBinding =
        FragmentAstroBinding.inflate(LayoutInflater.from(activity))

    override fun viewmodel() = AstroVM::class.java

    override fun initViews() {
        adapter = AstroAdapter()
        adapter.setOnItemClickListener(this)
        vb.recyclerview.adapter = adapter
        skeleton = Skeleton.bind(vb.recyclerview)
            .adapter(adapter)
            .load(R.layout.item_skeleton_astro)
            .shimmer(false)
            .show()
    }

    override fun initDatas() {
        vm.aostros.observe(this, Observer {
            val astros = it.getOrNull()
            if (astros != null) {
                adapter.setList(astros)
                vb.loadingview.visibility = View.GONE
            } else {
                showFailure(it.exceptionOrNull()!!.message!!, vb.loadingview)
            }
            skeleton.hide()
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val astro = this.adapter.data.get(position)
        val bundle = Bundle()
        bundle.putInt(AstroFortuneActivity.ASTROID, astro.astroid)
        IntentUtils.openActivity(activity, AstroFortuneActivity::class.java, bundle)
    }
}
