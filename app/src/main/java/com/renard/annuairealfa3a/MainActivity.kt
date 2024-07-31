package com.renard.annuairealfa3a

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val locationRequestCode = 1
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        // Check for location permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), locationRequestCode)
            } else {
                initializeWebView("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
            }
        } else {
            initializeWebView("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    private fun initializeWebView(url: String) {
        // Remove any existing WebView
        findViewById<ViewGroup>(R.id.webViewContainer).removeAllViews()

        // Create a new WebView
        webView = WebView(this).apply {
            webViewClient = CustomWebViewClient()
            webChromeClient = object : WebChromeClient() {
                override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
                    callback.invoke(origin, true, false)
                }
            }
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.setGeolocationEnabled(true)
            settings.cacheMode = if (isNetworkAvailable()) {
                WebSettings.LOAD_DEFAULT
            } else {
                WebSettings.LOAD_CACHE_ONLY
            }
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
                    if (isNetworkAvailable()) {
                        webView.loadUrl("https://nolannorofino.fr/app/alfa3a/anu/map.html")
                    } else {
                        Toast.makeText(this, "Utiliser la carte nécessite internet", Toast.LENGTH_LONG).show()
                    }
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationRequestCode) {
            if ((grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED))) {
                // Permission granted, initialize the WebView again to apply the permission
                initializeWebView("https://nolannorofino.fr/app/alfa3a/anu/anu.html")
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    fun onNetworkAvailable() {
        if (webView.url == "https://nolannorofino.fr/app/alfa3a/anu/anu.html" && webView.settings.cacheMode == WebSettings.LOAD_CACHE_ONLY) {
            webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
            webView.reload()
        }
    }
}
