package com.example.assignmentmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentmvvm.model.WeatherDao
import com.example.assignmentmvvm.model.WeatherService

class WeatherViewModelFactory(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherService, weatherDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
