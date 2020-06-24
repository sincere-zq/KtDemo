package com.witation.ktdemo.ui.act

import android.os.Build
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.witation.ktdemo.base.BAct
import com.witation.ktdemo.databinding.ActivityNewsDetailBinding
import kotlinx.android.synthetic.main.activity_news_detail.*


/**
 * 新闻详情页
 */
class NewsDetailActivity : BAct<ActivityNewsDetailBinding>() {
    companion object {
        const val NEWS_WEB_URL = "NEWS_WEB_URL"
    }

    override fun viewbind(): ActivityNewsDetailBinding =
        ActivityNewsDetailBinding.inflate(layoutInflater)

    override fun initViews() {
        initWebview()
    }

    override fun initDatas() {
        val newsWebUrl = intent.getStringExtra(NEWS_WEB_URL)
        vb.webview.loadUrl(newsWebUrl)
    }

    private fun initWebview() {
        val webSettings: WebSettings = vb.webview.getSettings()
        //支持缩放，默认为true。
        webSettings.setSupportZoom(false)
        //调整图片至适合webview的大小
        webSettings.useWideViewPort = true
        // 缩放至屏幕的大小
        webSettings.loadWithOverviewMode = true
        //设置默认编码
        webSettings.defaultTextEncodingName = "utf-8"
        //设置自动加载图片
        webSettings.loadsImagesAutomatically = true
        // 解决图片不显示
        webSettings.setBlockNetworkImage(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //多窗口
        webSettings.supportMultipleWindows()
        //获取触摸焦点
        webview.requestFocusFromTouch()
        //允许访问文件
        webSettings.setAllowFileAccess(true)
        //开启javascript
        webSettings.setJavaScriptEnabled(true)
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true)
        //支持内容重新布局
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)

        vb.webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean { //表示在当前的WebView继续打开网页
                view.loadUrl(request.url.toString())
                return true
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KEYCODE_BACK) && vb.webview.canGoBack()) {
            vb.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        vb.webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        vb.webview.clearHistory()
        (vb.webview.getParent() as ViewGroup).removeView(vb.webview)
        vb.webview.destroy()
        super.onDestroy()
    }
}
