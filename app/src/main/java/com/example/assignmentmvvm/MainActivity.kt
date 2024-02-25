package com.example.assignmentmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignmentmvvm.model.WeatherDatabase
import com.example.assignmentmvvm.model.WeatherService
import com.example.assignmentmvvm.ui.screens.DetailScreen
import com.example.assignmentmvvm.ui.screens.MainScreen
import com.example.assignmentmvvm.viewmodel.WeatherViewModel
import com.example.assignmentmvvm.viewmodel.WeatherViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit for WeatherService
/**
        val retrofit = Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/") // Base URL for Retrofit
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON conversion
            .build()
        */
        // Obtain an instance of WeatherService (initialization done in WeatherService companion object in the intreface definition of WeatherService)
        val weatherService = WeatherService.create()

        // Initialize Room database for WeatherDao
        val db = Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java, "weather-database" // Database name
        ).build()
        val weatherDao = db.weatherDao()

        // Create WeatherService instance
        //val weatherService = retrofit.create(WeatherService::class.java)

        // Initialize ViewModelFactory with WeatherService and WeatherDao
        val factory = WeatherViewModelFactory(weatherService, weatherDao)

        // Use the factory to instantiate the ViewModel
        val viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        setContent {
            // Use a single instance of viewModel created above
            MyApp(viewModel)
        }
    }
}

@Composable
fun MyApp(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            // Pass viewModel directly
            MainScreen(weatherViewModel = viewModel) {
                navController.navigate("detailScreen")
            }
        }
        composable("detailScreen") {
            // Pass viewModel directly
            DetailScreen(weatherViewModel = viewModel)
        }
    }
}



