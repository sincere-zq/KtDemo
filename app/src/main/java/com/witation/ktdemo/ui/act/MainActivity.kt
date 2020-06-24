package com.witation.ktdemo.ui.act

import android.widget.RadioGroup
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BVMAct
import com.witation.ktdemo.databinding.ActivityMainBinding
import com.witation.ktdemo.ui.frag.AstroFragment
import com.witation.ktdemo.ui.frag.NewsFragment
import com.witation.ktdemo.vm.MainVM

class MainActivity : BVMAct<ActivityMainBinding, MainVM>(), RadioGroup.OnCheckedChangeListener {

    override fun viewbind(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun viewmodel() = MainVM::class.java
    override fun initViews() {
        showFragment<NewsFragment>(R.id.fl_content)
        vb.tabLayout.setOnCheckedChangeListener(this)
    }

    override fun initDatas() {

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when {
            checkedId == R.id.tab_news -> showFragment<NewsFragment>(R.id.fl_content)
            else -> showFragment<AstroFragment>(R.id.fl_content)
        }
    }
}
