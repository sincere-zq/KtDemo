package com.witation.ktdemo.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.witation.ktdemo.base.BService
import com.witation.ktdemo.widget.LoadingView

fun toast(context: Context, content: String) {
    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
}

fun toast(context: Context, contentRes: Int) {
    Toast.makeText(context, contentRes, Toast.LENGTH_SHORT).show()
}

fun showFailure(message: String, view: LoadingView) {
    toast(view.context, message)
    view.setLoadText(message)
    view.visibility = View.VISIBLE
}

fun showFailure(currentPage: Int, message: String, view: LoadingView) {
    toast(view.context, message)
    if (currentPage == 0 || TextUtils.equals(BService.NET_ERROR, message)) {
        view.setLoadText(message)
        view.visibility = View.VISIBLE
    }
}