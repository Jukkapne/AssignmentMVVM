package com.example.assignmentmvvm.model

/**
 * Represents the weather data fetched from the weather API (after the line .addConverterFactory(GsonConverterFactory.create()) in your WeatherService setup with Retrofit which converts JSON responses into Kotlin objects.)
 *
 * This data class encapsulates the response structure expected from the weather API call.
 * It's designed to match the JSON response format, where each key-value pair in the JSON
 * corresponds to a property in this Kotlin data class.
 */
data class WeatherData(
    // A list of DayInfo objects representing weather information for individual days.
    // This allows us to handle API responses that include weather forecasts spanning multiple days.
    val days: List<DayInfo>
) {
    /**
     * Represents detailed weather information for a single day.
     *
     * Nested within WeatherData to logically group the day-specific information
     * as part of the overall weather data structure. This nesting reflects the JSON
     * structure where day-specific info is an array element within the "days" array.
     */
    data class DayInfo(
        // The date of the weather forecast, represented as a String.
        // It's important to format or parse this date as needed, depending on how the API returns it.
        val datetime: String,

        // The average temperature for the day, represented as a Double.
        // This temperature is metric-based, as specified in the API call parameters.
        val temp: Double,

        // A descriptive String of the weather conditions (e.g., "Sunny", "Rainy").
        // This text can be directly displayed in the UI to describe the day's weather.
        val conditions: String,

    )
}
