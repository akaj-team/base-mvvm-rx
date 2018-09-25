package com.android.asiantech.base.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.asiantech.rx_mvvm_base.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
