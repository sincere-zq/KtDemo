package com.witation.ktdemo.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.witation.ktdemo.R
import com.witation.ktdemo.model.NewsList
import com.witation.ktdemo.utils.loadRound
import java.text.MessageFormat

class NewsAdapter() :
    BaseQuickAdapter<NewsList, BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: NewsList) {
        holder.setText(R.id.tv_title, item.title)
            .setText(R.id.tv_src, MessageFormat.format("来源：{0}", item.src))
            .setText(R.id.tv_time, item.time)
        val imgTitle = holder.getView<ImageView>(R.id.img_title)
        loadRound(imgTitle, item.pic)
    }
}