package com.theleafapps.pro.weathernxt2.api

import com.theleafapps.pro.weathernxt2.utils.Constants.ENCODED_PATH
import com.theleafapps.pro.weathernxt2.utils.Constants.HOST
import com.theleafapps.pro.weathernxtgen.models.WeatherData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class WeatherApiService(
    private val baseUrl: String,
    private val client: HttpClient
) {
    fun getWeatherData(city: String, apiKey: String, units: String): Observable<WeatherData> {
        val url = URLBuilder().apply {
            protocol = URLProtocol.HTTPS
            host = HOST
            encodedPath = ENCODED_PATH
            parameters.append("q", city)
            parameters.append("appid", apiKey)
            parameters.append("units", units)
        }.build().toString()

        return Observable.create { emitter ->
            GlobalScope.async(Dispatchers.IO) {
                try {
                    val weatherData = client.get<WeatherData>(url)
                    emitter.onNext(weatherData)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }
    }
}