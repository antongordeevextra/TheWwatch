package com.example.thewatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

//    val handler = Handler(Looper.getMainLooper())
//
//    val customViewWatch = CustomViewWatch(Handler(Looper.getMainLooper()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
