package com.theleafapps.pro.weathernxt2.repository

import androidx.lifecycle.LiveData
import com.theleafapps.pro.weathernxt2.api.WeatherApiService
import com.theleafapps.pro.weathernxt2.db.WeatherDataDAO
import com.theleafapps.pro.weathernxt2.models.WeatherInfo
import javax.inject.Inject

class WeatherRepository @Inject
constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDataDao: WeatherDataDAO
) {
    fun insertWeatherInfo(weatherInfo: WeatherInfo) =
        weatherDataDao.insertWeatherInfo(weatherInfo)

    fun getWeatherInfoFromDB(wId: Int): LiveData<WeatherInfo> {
        return weatherDataDao.getLastCityWeatherInfo(wId)
    }
}