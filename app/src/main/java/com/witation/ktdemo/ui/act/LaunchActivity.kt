package com.witation.ktdemo.ui.act

import android.Manifest
import com.permissionx.guolindev.PermissionX
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BAct
import com.witation.ktdemo.databinding.ActivityLaunchBinding
import com.witation.ktdemo.utils.IntentUtils
import com.witation.ktdemo.utils.toast

/**
 * 启动页
 */
class LaunchActivity : BAct<ActivityLaunchBinding>() {

    override fun viewbind(): ActivityLaunchBinding = ActivityLaunchBinding.inflate(layoutInflater)

    override fun initViews() {

    }

    override fun initDatas() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .request { allGranted, grantedList, _ ->
                if (allGranted) {
                    IntentUtils.openActivity(this, MainActivity::class.java)
                    finish()
                } else {
                    toast(this, R.string.denied_permission)
                }
            }
    }
}
