package com.theleafapps.pro.weathernxtgen.models

import com.theleafapps.pro.weatherplay.models.Weather

data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val name: String,
    val wind: Wind
)