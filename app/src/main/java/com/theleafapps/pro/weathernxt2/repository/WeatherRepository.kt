package com.theleafapps.pro.weathernxt2.repository

import androidx.lifecycle.LiveData
import com.theleafapps.pro.weathernxt2.api.WeatherApiService
import com.theleafapps.pro.weathernxt2.db.WeatherDataDAO
import com.theleafapps.pro.weathernxt2.models.WeatherInfo
import com.theleafapps.pro.weathernxtgen.models.WeatherData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class WeatherRepository @Inject
constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDataDao: WeatherDataDAO
) {
    fun getWeather(cityName: String, apiKey: String, units: String): Observable<WeatherData> =
        weatherApiService.getWeatherData(cityName, apiKey, units)

    fun insertWeatherInfo(weatherInfo: WeatherInfo) =
        weatherDataDao.insertWeatherInfo(weatherInfo)

    fun getWeatherInfoFromDB(wId: Int): LiveData<WeatherInfo> {
        return weatherDataDao.getLastCityWeatherInfo(wId)
    }
}