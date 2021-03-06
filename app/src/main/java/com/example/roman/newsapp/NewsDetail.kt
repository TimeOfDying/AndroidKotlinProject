package com.example.roman.newsapp

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)


        webView.settings.javaScriptEnabled=true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object :WebViewClient()
        {
            override fun onPageFinished(view: WebView?, url: String?) {
               
            }
        }
        if(intent!=null)
            if(!intent.getStringExtra("webUrl").isEmpty())
                webView.loadUrl(intent.getStringExtra("webUrl"))
    }
}
