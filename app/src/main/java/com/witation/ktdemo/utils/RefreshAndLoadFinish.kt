package com.witation.ktdemo.utils

import com.scwang.smart.refresh.layout.SmartRefreshLayout


fun finishLoad(refrehLyaout: SmartRefreshLayout) {
    refrehLyaout.finishRefresh()
    refrehLyaout.finishLoadMore()
}