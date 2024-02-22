package com.example.assignmentmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentmvvm.model.WeatherDao
import com.example.assignmentmvvm.model.WeatherEntity
import com.example.assignmentmvvm.model.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherService: WeatherService, private val weatherDao: WeatherDao) : ViewModel() {

    private val _weatherCondition = MutableStateFlow("No data")
    val weatherCondition = _weatherCondition.asStateFlow()

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val weatherResponse = weatherService.getTodayWeather()
                val todayWeather = weatherResponse.days.firstOrNull()
                if (todayWeather != null) {
                    _weatherCondition.value = "Temp: ${todayWeather.temp}, Conditions: ${todayWeather.conditions}"
                } else {
                    _weatherCondition.value = "Weather data not available"
                }
            } catch (e: Exception) {
                _weatherCondition.value = "Failed to fetch weather: ${e.message}"
            }
        }
    }


    fun saveWeatherCondition(condition: String) {
        viewModelScope.launch {
            weatherDao.insertWeather(WeatherEntity(weatherCondition = condition))
        }
    }

    fun getLastSavedCondition() {
        viewModelScope.launch {
            weatherDao.getLastWeather()?.let {
                _weatherCondition.value = it.weatherCondition
            }
        }
    }
}
