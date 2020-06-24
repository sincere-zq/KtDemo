package com.witation.ktdemo.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BFrag
import com.witation.ktdemo.databinding.FragmentAstroBinding
import com.witation.ktdemo.ui.act.AstroFortuneActivity
import com.witation.ktdemo.ui.adapter.AstroAdapter
import com.witation.ktdemo.utils.IntentUtils

/**
 * 星座
 */
class TestFragment : BFrag<FragmentAstroBinding>(), OnItemClickListener {

    private lateinit var adapter: AstroAdapter
    private lateinit var skeleton: RecyclerViewSkeletonScreen

    override fun viewbind(): FragmentAstroBinding =
        FragmentAstroBinding.inflate(LayoutInflater.from(activity))

    override fun initViews() {
        adapter = AstroAdapter()
        adapter.setOnItemClickListener(this)
        vb.recyclerview.adapter = adapter
        skeleton = Skeleton.bind(vb.recyclerview)
            .adapter(adapter)
            .load(R.layout.item_skeleton_astro)
            .show()
    }

    override fun initDatas() {
        skeleton.hide()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val astro = this.adapter.data.get(position)
        val bundle = Bundle()
        bundle.putInt(AstroFortuneActivity.ASTROID, astro.astroid)
        IntentUtils.openActivity(activity, AstroFortuneActivity::class.java, bundle)
    }
}
