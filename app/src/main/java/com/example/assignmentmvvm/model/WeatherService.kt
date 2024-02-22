package com.example.assignmentmvvm.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherService {
    @GET("VisualCrossingWebServices/rest/services/timeline/Helsinki%2C%20finland?unitGroup=metric&key=4LFDB8ATNA8KHL8KBN7LVXG3G&contentType=json")
    suspend fun getTodayWeather(): WeatherData

    companion object {
        fun create(): WeatherService {
            return Retrofit.Builder()
                .baseUrl("https://weather.visualcrossing.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}
