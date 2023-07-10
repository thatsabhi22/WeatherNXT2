package com.theleafapps.pro.weathernxt2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theleafapps.pro.weathernxt2.R
import com.theleafapps.pro.weathernxt2.databinding.ActivityMainBinding
import com.theleafapps.pro.weathernxt2.utils.Constants.DEFAULT_CITY
import com.theleafapps.pro.weathernxt2.utils.Constants.wId
import com.theleafapps.pro.weathernxt2.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getWeatherDB(wId).observe(this) { weatherData ->
            weatherData?.let {
                populateViews(weatherData)
            } ?: run {
                viewModel.setDynamicArgument(DEFAULT_CITY)
                viewModel.getWeather()
            }
        }

    }
}