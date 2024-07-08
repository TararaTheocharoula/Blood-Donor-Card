package com.example.blooddonorcard

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class urlshowActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urlshow)

        var bbtn = findViewById<ImageButton>(R.id.bbtn);

        bbtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }

        //add url for the web view

        val mWebView = findViewById<View>(R.id.webView) as WebView
        mWebView!!.loadUrl("https://ekea.gr/tag/%ce%b5%ce%b8%ce%b5%ce%bb%ce%bf%ce%bd%cf%84%ce%b9%ce%ba%ce%ae-%ce%b1%ce%b9%ce%bc%ce%bf%ce%b4%ce%bf%cf%83%ce%af%ce%b1/")

        //dissable javascript and we have a web view by difault
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()

        mWebView.canGoBack()
        mWebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK

                && event.action == MotionEvent.ACTION_UP
                && mWebView.canGoBack()) {

                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        })


    }
}