package com.theleafapps.pro.weathernxt2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theleafapps.pro.weathernxt2.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
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
}