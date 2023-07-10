package com.theleafapps.pro.weathernxt2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theleafapps.pro.weathernxt2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}