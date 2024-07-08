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

class urlshowActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urlshow2)


            //add url for the web view

            val mWebView = findViewById<View>(R.id.webView) as WebView
            mWebView!!.loadUrl("https://www.google.com/maps/search/%CE%B1%CE%B9%CE%BC%CE%BF%CE%B4%CE%BF%CF%83%CE%B9%CE%B1+%CF%83%CF%84%CE%B7%CE%BD+%CE%B5%CE%BB%CE%BB%CE%B1%CE%B4%CE%B1/@40.6096961,19.3006411,7z/data=!3m1!4b1")

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