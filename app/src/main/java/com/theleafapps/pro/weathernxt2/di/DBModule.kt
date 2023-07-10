package com.theleafapps.pro.weathernxt2.di

import android.content.Context
import androidx.room.Room
import com.theleafapps.pro.weathernxt2.db.WeatherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDB {
        return Room.databaseBuilder(
            context,
            WeatherDB::class.java,
            "weatherData.db"
        ).build()
    }
}