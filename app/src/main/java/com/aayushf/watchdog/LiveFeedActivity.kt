package com.aayushf.watchdog

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_live_feed.*
import kotlinx.android.synthetic.main.content_live_feed.*
import android.webkit.WebViewClient



class LiveFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_feed)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        live_web_view.setWebViewClient(WebViewClient())
        live_web_view.loadUrl("http://192.168.43.42:8000/")
    }

}
