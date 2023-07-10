package com.theleafapps.pro.weathernxt2.utils

import com.theleafapps.pro.weathernxt2.models.WeatherInfo
import com.theleafapps.pro.weathernxtgen.models.WeatherData

object TransformUtil {
    public fun extractWeatherInfoFromResponse(weatherData:WeatherData): WeatherInfo? {
        val weatherInfo = WeatherInfo(
            id = 1,
            city_name = weatherData.name.toString(),
            temperature = weatherData.main.temp.toString(),
            pressure = weatherData.main.pressure.toString(),
            windSpeed = weatherData.wind.speed.toString(),
            humidity = weatherData.main.humidity.toString(),
            visibility = weatherData.visibility.toString(),
            sunrise = weatherData.sys.sunrise,
            sunset = weatherData.sys.sunset,
            detail = weatherData.weather[0].main.toString()
        )
        return weatherInfo
    }
}