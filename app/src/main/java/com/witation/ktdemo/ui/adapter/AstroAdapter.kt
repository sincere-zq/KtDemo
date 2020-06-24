package com.witation.ktdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.witation.ktdemo.R
import com.witation.ktdemo.model.Astro
import com.witation.ktdemo.utils.loadRound
import java.text.MessageFormat

/**
 * 星座适配器
 */
class AstroAdapter : BaseQuickAdapter<Astro, BaseViewHolder>(R.layout.item_astro) {
    override fun convert(holder: BaseViewHolder, item: Astro) {
        holder.setText(R.id.tv_astro, MessageFormat.format("{0}  {1}", item.astroname, item.date))
        loadRound(holder.getView(R.id.img_astro), item.pic)
    }
}