package com.example.minipaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ClippingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_clippting_example)
    setContentView(ClippedView(this))
    }
}
