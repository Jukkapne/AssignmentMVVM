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
    // Assuming weatherCondition is now of type WeatherData? and not String
    val weatherCondition by weatherViewModel.weatherCondition.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { weatherViewModel.fetchWeather() }) {
            Text("Fetch Weather")
        }

        // Check if weatherData is not null and then iterate over days
        weatherCondition?.days?.forEach { day ->
            Text(text = "Date: ${day.datetime}", modifier = Modifier.padding(8.dp))
            Text(text = "Temp: ${day.temp}°C, Conditions: ${day.conditions}", modifier = Modifier.padding(8.dp))
            Text(text = "Humidity: ${day.humidity}%, Precipitation: ${day.precip}mm", modifier = Modifier.padding(8.dp))
            day.snow?.let {
                Text(text = "Snow: ${it}cm", modifier = Modifier.padding(8.dp))
            }
        }

        Button(onClick = {
            // Here, you might need to adjust how you save weather conditions,
            // since weatherCondition.toString() is no longer appropriate.
            // You might choose to save a specific day's summary or implement a method in your ViewModel to handle saving.
            navigateToDetail()
        }) {
            Text("Save and Go to Detail")
        }
    }
}