package com.witation.ktdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.witation.ktdemo.R
import com.witation.ktdemo.model.Place
import com.witation.ktdemo.utils.loadRound

class PlaceAdapter : BaseQuickAdapter<Place, BaseViewHolder>(R.layout.item_place) {
    override fun convert(holder: BaseViewHolder, item: Place) {

        holder.setText(R.id.tv_place_name, item.name)
            .setText(R.id.tv_place_address, item.address)

        loadRound(
            holder.getView(R.id.img_head),
            "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg"
        )
    }
}