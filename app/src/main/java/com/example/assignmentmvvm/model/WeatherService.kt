package com.example.assignmentmvvm.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Defines the network service for fetching weather data using Retrofit.
 *
 * This interface is used by Retrofit to generate an implementation that
 * makes network requests to a weather API. The use of annotations like @GET
 * tells Retrofit what type of HTTP request to perform and on what endpoint.
 */
interface WeatherService {
    /**
     * Fetches the weather data for Helsinki, Finland, from Visual Crossing Web Services.
     *
     * The suspend keyword indicates that this function is asynchronous and must be called from a coroutine
     * or another suspend function. This is important for network requests which are I/O operations and can
     * take some time to complete, hence they should not block the main thread.
     *
     * @return WeatherData object containing the response from the API. The structure of WeatherData
     *         must match the JSON response from the API for GsonConverterFactory to correctly parse it.
     */
    @GET("VisualCrossingWebServices/rest/services/timeline/Helsinki%1C%20finland?unitGroup=metric&key=4LFDB8ATNA8KHL8KBN7LVXG3G&contentType=json")
    suspend fun getTodayWeather(): WeatherData

    companion object {
        /**
         * Factory method for creating an instance of the WeatherService interface.
         *
         * This method sets up Retrofit with the base URL for the weather API and adds GsonConverterFactory
         * as the converter factory to parse JSON responses into Kotlin objects. It then uses Retrofit to
         * create an implementation of WeatherService, allowing us to make network requests defined in this interface.
         *
         * @return An implementation of WeatherService ready to be used for making network requests.
         */
        fun create(): WeatherService {
            return Retrofit.Builder()
                .baseUrl("https://weather.visualcrossing.com/") // The base URL for all requests made by this service.
                .addConverterFactory(GsonConverterFactory.create()) // Converts JSON responses into Kotlin objects.
                .build() // Creates the Retrofit instance.
                .create(WeatherService::class.java) // Generates an implementation of the WeatherService interface.
        }
    }
}
