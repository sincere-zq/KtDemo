package com.witation.ktdemo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.witation.ktdemo.R

val normalOptions: RequestOptions = RequestOptions()
    .transform(CenterCrop())
    .placeholder(R.drawable.shape_placeholder)
    .error(R.drawable.shape_placeholder)


val circleOptions: RequestOptions = RequestOptions()
    .transform(CenterCrop(), CircleCrop())
    .placeholder(R.drawable.shape_placeholder)
    .error(R.drawable.shape_placeholder)

val roundOptions: RequestOptions = RequestOptions()
    .transform(CenterCrop(), RoundedCorners(5))
    .placeholder(R.drawable.shape_placeholder)
    .error(R.drawable.shape_placeholder)


fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .apply(normalOptions)
        .into(view)
}

fun loadCircle(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .circleCrop()
        .apply(circleOptions)
        .into(view)
}

fun loadRound(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .apply(roundOptions)
        .into(view)
}