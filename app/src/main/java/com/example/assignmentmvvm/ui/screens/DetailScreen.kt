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
fun DetailScreen(weatherViewModel: WeatherViewModel) {
    // Weather data is fetched from the ROOM database, hence string is used
    val weatherconditionString by weatherViewModel.weatherConditionString.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { weatherViewModel.getLastSavedCondition() }) {
            Text("Load Last Saved Weather")
        }
        Text(text = weatherconditionString, modifier = Modifier.padding(8.dp))
    }
}