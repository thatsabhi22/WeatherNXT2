package com.theleafapps.pro.weathernxtgen.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherInfo")
data class WeatherInfo (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "city_name") val city_name: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "pressure") val pressure: String?,
    @ColumnInfo(name = "windSpeed") val windSpeed: String?,
    @ColumnInfo(name = "humidity") val humidity: String?,
    @ColumnInfo(name = "visibility") val visibility: String?,
    @ColumnInfo(name = "sunrise") val sunrise: Int?,
    @ColumnInfo(name = "sunset") val sunset: Int?,
    @ColumnInfo(name = "detail") val detail: String?
)