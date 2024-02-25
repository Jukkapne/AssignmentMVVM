package com.example.assignmentmvvm.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignmentmvvm.viewmodel.WeatherViewModel

@Composable
fun MainScreen(weatherViewModel: WeatherViewModel, navigateToDetail: () -> Unit) {
    val weatherCondition by weatherViewModel.weatherCondition.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { weatherViewModel.fetchWeather() }) {
            Text("Fetch Weather")
        }
        // Check if weatherData is not null and then iterate over days
        weatherCondition?.days?.forEach { day ->
            Text(text = "Date: ${day.datetime}", modifier = Modifier.padding(8.dp))
        }

        Button(onClick = {
            weatherViewModel.saveWeatherCondition(weatherCondition?.days?.toString() ?: "No weather data")
            navigateToDetail()
        }) {
            Text("Save and Go to Detail")
        }
    }
}