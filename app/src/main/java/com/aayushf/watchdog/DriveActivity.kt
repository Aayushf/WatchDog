package com.aayushf.watchdog

import android.os.Bundle
import android.view.MotionEvent
import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_drive.*
import kotlinx.android.synthetic.main.content_drive.*
import kotlinx.android.synthetic.main.content_live_feed.*

class DriveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive)
        setSupportActionBar(toolbar)
        live_web_view.setWebViewClient(WebViewClient())
        live_web_view.loadUrl("http://192.168.43.42:8000/")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val db = FirebaseDatabase.getInstance()
        val directionref = db.getReference("direction")
        drive_right.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                directionref.setValue("r")
            }else if (event.action == MotionEvent.ACTION_UP){
                directionref.setValue("s")
            }
            true
        }
        drive_left.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                directionref.setValue("l")
            }else if (event.action == MotionEvent.ACTION_UP){
                directionref.setValue("s")
            }
            true
        }
        drive_fwd.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                directionref.setValue("f")
            }else if (event.action == MotionEvent.ACTION_UP){
                directionref.setValue("s")
            }
            true
        }
        drive_back.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                directionref.setValue("b")
            }else if (event.action == MotionEvent.ACTION_UP){
                directionref.setValue("s")
            }
            true
        }
    }

}
