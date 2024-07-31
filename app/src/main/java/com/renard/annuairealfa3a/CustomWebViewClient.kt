package com.renard.annuairealfa3a

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return when {
            url.startsWith("http://") || url.startsWith("https://") -> {
                if (url.startsWith("https://waze.com/") || url.startsWith("https://www.waze.com/")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    view.context.startActivity(intent)
                    true
                } else {
                    view.loadUrl(url)
                    false
                }
            }
            url.startsWith("tel:") -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                view.context.startActivity(intent)
                true
            }
            url.startsWith("geo:") -> {
                val geoUri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, geoUri)
                // Add "q" parameter to the intent to support displaying information in maps apps
                intent.data = Uri.parse("geo:0,0?q=" + geoUri.schemeSpecificPart)
                view.context.startActivity(intent)
                true
            }
            else -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
                true
            }
        }
    }
}
