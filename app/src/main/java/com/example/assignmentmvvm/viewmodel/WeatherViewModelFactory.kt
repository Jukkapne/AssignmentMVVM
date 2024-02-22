package com.example.assignmentmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentmvvm.model.WeatherDao
import com.example.assignmentmvvm.model.WeatherService

/**
 * Factory class for creating instances of WeatherViewModel. (The factory’s goal is to deliver a ready-to-use object to its client.)
 *
 * ViewModel instances may require dependencies to perform their functions.
 * For example, WeatherViewModel requires access to WeatherService and WeatherDao
 * to interact with the network and database respectively. Since ViewModelProvider
 * does not support direct injection of these dependencies into the ViewModel's constructor,
 * a factory is used to manually provide these dependencies.
 */
class WeatherViewModelFactory(
    // The WeatherService instance. This service is used by the ViewModel to make network requests
    // for fetching weather data. Without this, the ViewModel wouldn't be able to access remote weather information.
    private val weatherService: WeatherService,

    // The WeatherDao instance. This DAO (Data Access Object) is used by the ViewModel to interact
    // with the Room database for storing and retrieving weather data. It is essential for persisting
    // weather data locally, allowing the app to display weather information even when offline or to cache data.
    private val weatherDao: WeatherDao
): ViewModelProvider.Factory {

    /**
     * Creates an instance of the specified ViewModel class, if it is WeatherViewModel.
     *
     * @param modelClass The class of the ViewModel to instantiate.
     * @return A new instance of WeatherViewModel with weatherService and weatherDao passed as parameters.
     * @throws IllegalArgumentException if the requested ViewModel class is not assignable from WeatherViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the modelClass is WeatherViewModel to ensure this factory only creates instances of that specific ViewModel.
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            // Suppresses the unchecked cast warning because the type has been checked.
            // Returns a new instance of WeatherViewModel, providing it with the necessary dependencies.
            // This allows WeatherViewModel to perform network operations and database transactions,
            // which are crucial for its functionality.
            return WeatherViewModel(weatherService, weatherDao) as T
        }
        // If the requested ViewModel isn't WeatherViewModel, throw an exception. This ensures the factory's integrity
        // by only producing the type of ViewModel it's designed to create.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
