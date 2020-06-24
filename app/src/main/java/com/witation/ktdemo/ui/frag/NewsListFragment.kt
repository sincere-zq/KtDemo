package com.witation.ktdemo.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BVMFrag
import com.witation.ktdemo.databinding.FragmentNewsListBinding
import com.witation.ktdemo.model.NewsList
import com.witation.ktdemo.ui.act.NewsDetailActivity
import com.witation.ktdemo.ui.adapter.NewsAdapter
import com.witation.ktdemo.utils.DividerDecoration
import com.witation.ktdemo.utils.IntentUtils
import com.witation.ktdemo.utils.finishLoad
import com.witation.ktdemo.utils.showFailure
import com.witation.ktdemo.vm.NewsVM

/**
 * 新闻列表
 */
class NewsListFragment : BVMFrag<FragmentNewsListBinding, NewsVM>(),
    OnRefreshLoadMoreListener,
    OnItemClickListener {
    companion object {
        const val CHANNEL = "CHANNEL"
    }

    //适配器
    private lateinit var adapter: NewsAdapter
    //当前页数
    private var currentPage = 0
    //骨架
    private lateinit var skeleton: SkeletonScreen

    override fun viewbind(): FragmentNewsListBinding =
        FragmentNewsListBinding.inflate(LayoutInflater.from(activity))

    override fun viewmodel() = NewsVM::class.java

    override fun initViews() {
        adapter = NewsAdapter()
        adapter.setOnItemClickListener(this)
        vb.refreshLayout.setOnRefreshLoadMoreListener(this)
        vb.recyclerview.addItemDecoration(
            DividerDecoration(
                activity,
                LinearLayoutManager.VERTICAL,
                R.drawable.shape_rv_space_divider
            )
        )
        vb.recyclerview.adapter = adapter

        skeleton = Skeleton.bind(vb.recyclerview)
            .adapter(adapter)
            .load(R.layout.item_skeleton_news)
            .shimmer(false)
            .show()
    }

    override fun initDatas() {
        vm.newsList.observe(this, Observer {
            val newsList = it.getOrNull()
            if (newsList != null) {
                setNewsList(newsList.list)
                vb.loadingview.visibility = View.GONE
            } else {
                showFailure(currentPage, it.exceptionOrNull()!!.message!!, vb.loadingview)
                finishLoad(vb.refreshLayout)
            }
            if (currentPage == 0) {
                skeleton.hide()
            }
        })
        vm.currentChannel = arguments!!.getString(CHANNEL).toString()
        onRefresh(vb.refreshLayout)
    }

    /**
     * 设置新闻列表数据
     */
    private fun setNewsList(newsList: List<NewsList>) {
        if (currentPage == 0) {
            adapter.setList(newsList)
            vb.refreshLayout.finishRefresh()
        } else {
            adapter.addData(newsList)
            vb.refreshLayout.finishLoadMore(0, true, newsList.size < 10)
        }
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage += 1
        vm.currentPage(currentPage)
    }

    /**
     * 刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 0
        vm.currentPage(currentPage)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val bundle = Bundle()
        bundle.putString(NewsDetailActivity.NEWS_WEB_URL, this.adapter.data.get(position).weburl)
        IntentUtils.openActivity(activity, NewsDetailActivity::class.java, bundle)
    }
}
