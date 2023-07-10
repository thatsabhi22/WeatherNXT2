package com.theleafapps.pro.weathernxt2.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theleafapps.pro.weathernxt2.models.WeatherInfo
import com.theleafapps.pro.weathernxt2.repository.WeatherRepository
import com.theleafapps.pro.weathernxt2.utils.Constants.api_key
import com.theleafapps.pro.weathernxt2.utils.Constants.units
import com.theleafapps.pro.weathernxt2.utils.TransformUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val dynamicArgumentLiveData = MutableLiveData<String>()
    private var errorMsg: String = ""

    fun setDynamicArgument(dynamicArgument: String) {
        dynamicArgumentLiveData.value = dynamicArgument
    }

    fun getErrorMsg(): String {
        return errorMsg
    }

    fun getWeather() {
        dynamicArgumentLiveData.value?.let { dynamicArgument ->
            val disposable = weatherRepository.getWeather(dynamicArgument, api_key, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable { response ->
                    Completable.fromCallable {
                        val weatherInfo =
                            TransformUtil.extractWeatherInfoFromResponse(response)
                        weatherInfo?.let {
                            weatherRepository.insertWeatherInfo(weatherInfo)
                        }
                    }.subscribeOn(Schedulers.io())
                }.subscribe({
                    // Success
                    errorMsg = ""
                }, { error ->
                    // Error
                    errorMsg = "City Not Foung"
                    Log.d("tag", "getWeather Error: $errorMsg")
                })
            disposables.add(disposable)
        }
    }

    fun getWeatherDB(wId: Int): LiveData<WeatherInfo> {
        return weatherRepository.getWeatherInfoFromDB(wId)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}