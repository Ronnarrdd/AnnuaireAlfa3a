package com.renard.annuairealfa3a

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWebView("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
    }

    private fun initializeWebView(url: String) {
        // Remove any existing WebView
        findViewById<ViewGroup>(R.id.webViewContainer)?.removeAllViews()

        // Create a new WebView
        webView = WebView(this).apply {
            webViewClient = CustomWebViewClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            loadUrl(url)
        }

        // Add the new WebView to the container
        findViewById<ViewGroup>(R.id.webViewContainer).addView(webView)

        // Set up swipe gesture listener
        setupSwipeGestureListener()
    }

    private fun setupSwipeGestureListener() {
        val swipeGestureListener = SwipeGestureListener(
            onSwipeRight = {
                // Do nothing on right swipe
            },
            onSwipeLeft = {
                if (webView.url == "https://nolannorofino.fr/app/alfa3a/anu/anu.html") {
                    webView.loadUrl("https://nolannorofino.fr/app/alfa3a/anu/map.html")
                }
            }
        )

        webView.setOnTouchListener { v, event ->
            val handledBySwipe = swipeGestureListener.onTouch(v, event)
            if (!handledBySwipe) {
                v?.onTouchEvent(event) ?: true
            } else {
                true
            }
        }
    }

    override fun onBackPressed() {
        if (webView.url == "https://nolannorofino.fr/app/alfa3a/anu/map.html") {
            initializeWebView("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
        } else if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
