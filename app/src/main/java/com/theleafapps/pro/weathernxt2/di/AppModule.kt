package com.theleafapps.pro.weathernxt2.di

import com.theleafapps.pro.weathernxt2.api.WeatherApiService
import com.theleafapps.pro.weathernxt2.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }

        val baseUrl = Constants.BASE_URL
        return WeatherApiService(baseUrl, client)
    }

}