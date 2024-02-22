package com.example.assignmentmvvm.model

data class WeatherData(
    val days: List<DayInfo>
) {
    data class DayInfo(
        val datetime: String, // Assuming this is the date
        val temp: Double,
        val conditions: String
    )
}
