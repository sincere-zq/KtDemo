package com.witation.ktdemo.ui.act

import android.view.View
import androidx.lifecycle.Observer
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.witation.ktdemo.R
import com.witation.ktdemo.base.BVMAct
import com.witation.ktdemo.databinding.ActivityAstroFortuneBinding
import com.witation.ktdemo.model.AstroFortunneResult
import com.witation.ktdemo.utils.showFailure
import com.witation.ktdemo.vm.AstroVM
import java.text.MessageFormat

/**
 * 星座有运势
 */
class AstroFortuneActivity : BVMAct<ActivityAstroFortuneBinding, AstroVM>() {
    companion object {
        const val ASTROID = "ASTROID"
    }

    private lateinit var skeleton: SkeletonScreen
    override fun viewbind(): ActivityAstroFortuneBinding =
        ActivityAstroFortuneBinding.inflate(layoutInflater)

    override fun viewmodel() = AstroVM::class.java
    override fun initViews() {
        skeleton = Skeleton.bind(vb.scrollview)
            .load(R.layout.activity_skeleton_astro_fortune)
            .show()
    }

    override fun initDatas() {
        val astroid = intent.getIntExtra(ASTROID, 0)
        vm.selectedAostroFortune.observe(this, Observer {
            val result = it.getOrNull()
            if (result != null) {
                setAstrofortune(result)
                vb.loadingview.visibility = View.GONE
            } else {
                showFailure(it.exceptionOrNull()!!.message!!, vb.loadingview)
            }
            skeleton.hide()
        })
        vm.aostroid(astroid)
    }

    /**
     * 设置星座运势数据
     */
    private fun setAstrofortune(astrofortune: AstroFortunneResult) {

        vb.tvAstroName.setText(astrofortune.astroname)

        vb.tvTodayFortune.setText(MessageFormat.format("今日运势：{0}", astrofortune.today.date))
        vb.tvTodayPresummary.setText(MessageFormat.format("概述：{0}", astrofortune.today.presummary))
        vb.tvTodayStar.setText(MessageFormat.format("贵人星座：{0}", astrofortune.today.star))
        vb.tvTodayColor.setText(MessageFormat.format("幸运颜色：{0}", astrofortune.today.color))
        vb.tvTodayNumber.setText(MessageFormat.format("幸运数字：{0}", astrofortune.today.number))
        vb.tvTodayMoney.setText(MessageFormat.format("财运运势：{0}", astrofortune.today.money))
        vb.tvTodayCareer.setText(MessageFormat.format("工作运势：{0}", astrofortune.today.career))
        vb.tvTodayLove.setText(MessageFormat.format("爱情运势：{0}", astrofortune.today.love))
        vb.tvTodayHealth.setText(MessageFormat.format("健康运势：{0}", astrofortune.today.health))
        vb.tvTodaySummary.setText(MessageFormat.format("综合运势：{0}", astrofortune.today.summary))

        vb.tvTomorrowFortune.setText(MessageFormat.format("明日运势：{0}", astrofortune.tomorrow.date))
        vb.tvTomorrowPresummary.setText(
            MessageFormat.format(
                "概述：{0}",
                astrofortune.tomorrow.presummary
            )
        )
        vb.tvTomorrowStar.setText(MessageFormat.format("贵人星座：{0}", astrofortune.tomorrow.star))
        vb.tvTomorrowColor.setText(MessageFormat.format("幸运颜色：{0}", astrofortune.tomorrow.color))
        vb.tvTomorrowNumber.setText(MessageFormat.format("幸运数字：{0}", astrofortune.tomorrow.number))
        vb.tvTomorrowMoney.setText(MessageFormat.format("财运运势：{0}", astrofortune.tomorrow.money))
        vb.tvTomorrowCareer.setText(MessageFormat.format("工作运势：{0}", astrofortune.tomorrow.career))
        vb.tvTomorrowLove.setText(MessageFormat.format("爱情运势：{0}", astrofortune.tomorrow.love))
        vb.tvTomorrowHealth.setText(MessageFormat.format("健康运势：{0}", astrofortune.tomorrow.health))
        vb.tvTomorrowSummary.setText(
            MessageFormat.format(
                "综合运势：{0}",
                astrofortune.tomorrow.summary
            )
        )

        vb.tvWeekFortune.setText(MessageFormat.format("本周运势：{0}", astrofortune.week.date))
        vb.tvWeekMoney.setText(MessageFormat.format("财运运势：{0}", astrofortune.week.money))
        vb.tvWeekCareer.setText(MessageFormat.format("工作运势：{0}", astrofortune.week.career))
        vb.tvWeekLove.setText(MessageFormat.format("爱情运势：{0}", astrofortune.week.love))
        vb.tvWeekHealth.setText(MessageFormat.format("健康运势：{0}", astrofortune.week.health))
        vb.tvWeekJob.setText(MessageFormat.format("求职运势：{0}", astrofortune.week.job))

        vb.tvMonthFortune.setText(MessageFormat.format("本月运势：{0}", astrofortune.month.date))
        vb.tvMonthSummary.setText(MessageFormat.format("综合运势：{0}", astrofortune.month.summary))
        vb.tvMonthMoney.setText(MessageFormat.format("财运运势：{0}", astrofortune.month.money))
        vb.tvMonthCareer.setText(MessageFormat.format("工作运势：{0}", astrofortune.month.career))
        vb.tvMonthLove.setText(MessageFormat.format("爱情运势：{0}", astrofortune.month.love))

        vb.tvYearFortune.setText(MessageFormat.format("本年运势：{0}", astrofortune.year.date))
        vb.tvYearSummary.setText(MessageFormat.format("综合运势：{0}", astrofortune.year.summary))
        vb.tvYearMoney.setText(MessageFormat.format("财运运势：{0}", astrofortune.year.money))
        vb.tvYearCareer.setText(MessageFormat.format("工作运势：{0}", astrofortune.year.career))
        vb.tvYearLove.setText(MessageFormat.format("爱情运势：{0}", astrofortune.year.love))
    }
}
