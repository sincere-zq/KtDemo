package com.witation.ktdemo.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsChannelAdapter(
    fm: FragmentManager,
    val titles: List<String>,
    val fragList: List<Fragment>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return fragList.get(position)
    }

    override fun getCount(): Int {
        return fragList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}