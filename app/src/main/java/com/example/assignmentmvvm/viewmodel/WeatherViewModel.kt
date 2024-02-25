package com.example.assignmentmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentmvvm.model.WeatherDao
import com.example.assignmentmvvm.model.WeatherData
import com.example.assignmentmvvm.model.WeatherEntity
import com.example.assignmentmvvm.model.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Defines WeatherViewModel which is responsible for preparing and managing the data for the UI,
// including making calls to get data from a network or database, and handling any data operations.
class WeatherViewModel(private val weatherService: WeatherService, private val weatherDao: WeatherDao) : ViewModel() {

    // A private mutable state flow to hold the current weather condition. MutableStateFlow allows us to update its value.
    private val _weatherCondition = MutableStateFlow<WeatherData?>(null)
    // A public read-only version of the weather condition state flow. This is what the UI will observe for updates.
    val weatherCondition = _weatherCondition.asStateFlow()
    //val weatherCondition: StateFlow<WeatherData?> = _weatherCondition  //just another way to write the above line

    // A private mutable state flow to hold the weather condition string from the database. MutableStateFlow allows us to update its value.
    private val _weatherConditionString = MutableStateFlow<String>("No data available")
    val weatherConditionString = _weatherConditionString.asStateFlow()
    //val weatherConditionString: StateFlow<String> = _weatherConditionString  //just another way to write the above line


    // A private mutable state flow to hold the current error state. MutableStateFlow allows us to update its value.
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()
    //val errorState: StateFlow<String?> = _errorState  //just another way to write the above line

    // Function to fetch the current weather conditions from the network.
    fun fetchWeather() {
        // Launches a new coroutine without blocking the current thread and calls the network service within the coroutine.
        viewModelScope.launch {
            try {
                // Attempt to fetch today's weather using the WeatherService.
                val weatherResponse = weatherService.getTodayWeather()
                // Try to get the first day's weather from the response, if available.
                val todayWeather = weatherResponse.days.firstOrNull()
                if (todayWeather != null) {

                    // If today's weather is available, update the weather condition state with the temperature and conditions.
                    _weatherCondition.value = weatherResponse
                    _errorState.value = null // Clear any previous error

                } else {
                    // If no weather data is available, update the state to indicate that.
                    _errorState.value = "Failed to fetch weather, no data available."
                }
            } catch (e: Exception) {
                // If an error occurs during the network request, update the state to indicate the failure.
                _errorState.value = "Failed to fetch weather: ${e.message}"
            }
        }
    }

    // Function to save a weather condition string to the database.
    fun saveWeatherCondition(condition: String) {
        // Launches a coroutine for database access to insert the weather condition.
        viewModelScope.launch {
            weatherDao.insertWeather(WeatherEntity(weatherCondition = condition))
        }
    }

    // Function to retrieve the last saved weather condition from the database.
    fun getLastSavedCondition() {
        // Launches a coroutine for database access to query the last weather condition.
        viewModelScope.launch {
            // Use Kotlin's safe call operator `?.let` to handle the case where getLastWeather returns null.
            weatherDao.getLastWeather()?.let {
                // If a last saved weather condition is found, update the state to show this condition.
                _weatherConditionString.value = it.weatherCondition
            }
        }
    }
}
