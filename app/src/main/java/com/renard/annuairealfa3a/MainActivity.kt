package com.renard.annuairealfa3a

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        webView.webViewClient = CustomWebViewClient()

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Enable DOM storage
        webView.settings.domStorageEnabled = true

        // Enable database storage
        webView.settings.databaseEnabled = true

        webView.loadUrl("https://nolannorofino.fr/app/alfa3a/anu/anu.html")

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
            webView.loadUrl("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
        } else if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
