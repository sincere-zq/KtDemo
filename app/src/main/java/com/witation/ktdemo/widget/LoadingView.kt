package com.witation.ktdemo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ethanhua.skeleton.ViewReplacer
import com.witation.ktdemo.R
import com.witation.ktdemo.databinding.LayoutLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: LayoutLoadingBinding
    private lateinit var viewReplacer: ViewReplacer

    init {
        binding = LayoutLoadingBinding.inflate(LayoutInflater.from(context), this, true)
        viewReplacer = ViewReplacer(binding.loadingLayout)
    }

    //设置展示内容
    fun setLoadText(content: String?) {
        viewReplacer.restore()
        binding.tvLoadResult.text = content
    }

    //设置展示内容
    fun setLoadText(content: Int) {
        viewReplacer.restore()
        binding.tvLoadResult.setText(content)
    }

    //设置展示内容
    fun setLoadImage(content: Int) {
        viewReplacer.restore()
        binding.imgLoading.setImageResource(content)
    }

    fun loading() {
        viewReplacer.replace(R.layout.layout_progress)
    }
}