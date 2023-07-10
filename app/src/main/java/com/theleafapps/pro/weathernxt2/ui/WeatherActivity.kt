package com.theleafapps.pro.weathernxt2.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.theleafapps.pro.weathernxt2.databinding.ActivityMainBinding
import com.theleafapps.pro.weathernxt2.models.WeatherInfo
import com.theleafapps.pro.weathernxt2.utils.Constants.DEFAULT_CITY
import com.theleafapps.pro.weathernxt2.utils.Constants.HUMIDITY_UNIT
import com.theleafapps.pro.weathernxt2.utils.Constants.PRESSURE_UNIT
import com.theleafapps.pro.weathernxt2.utils.Constants.VISIBILITY_UNIT
import com.theleafapps.pro.weathernxt2.utils.Constants.WINDSPEED_UNIT
import com.theleafapps.pro.weathernxt2.utils.Constants.wId
import com.theleafapps.pro.weathernxt2.utils.Utility
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

        binding.getCityWeatherDataBtn.setOnClickListener {
            val city = binding.etCity.text.toString()
            if (city.isNotEmpty()) {
                binding.locationLoading.visibility = View.VISIBLE
                viewModel.setDynamicArgument(city)
                viewModel.getWeather()
                binding.locationLoading.visibility = View.GONE
                binding.errorMsg.visibility = View.VISIBLE
                binding.errorMsg.text = viewModel.getErrorMsg()
                hideKeyboard()
            } else {
                binding.etCity.error = "Enter Something"
            }
        }
    }

    private fun populateViews(weatherData: WeatherInfo) {
        binding.apply {
            binding.tvCityName.text = weatherData.city_name.toString()
            binding.etCity.setText(weatherData.city_name.toString())
            val temp = weatherData.temperature
            binding.tvTemperature.text = temp?.indexOf(".")?.let { temp.substring(0, it) }
            binding.tvPressure.text =
                weatherData.pressure.toString().plus(PRESSURE_UNIT)
            binding.tvWind.text = weatherData.windSpeed.toString().plus(WINDSPEED_UNIT)
            binding.tvHumidity.text =
                weatherData.humidity.toString().plus(HUMIDITY_UNIT)
            binding.tvVisibility.text =
                ((weatherData.visibility?.toFloat()!! / 1000).toString()).plus(
                    VISIBILITY_UNIT
                )
            binding.tvSunrise.text = Utility.getTime(weatherData.sunrise.toString())
            binding.tvSunset.text = Utility.getTime(weatherData.sunset.toString())
            binding.weatherInfo.text = weatherData.detail.toString()

        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}