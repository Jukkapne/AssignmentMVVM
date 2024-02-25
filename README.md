# AssignmentMVVM
 
This repository contains a simple weather application built using Kotlin and Jetpack Compose, designed to demonstrate the Model-View-ViewModel (MVVM) architecture pattern. It showcases fetching weather data from a REST API, displaying it in a user interface, and saving it to a local database using Room.

Structure
The project is organized into several key packages, each serving a distinct role in the application architecture:

model: Contains data classes and interfaces for managing the application's data. It includes the WeatherService interface for API calls and the WeatherData data class to represent the weather information.

viewmodel: Houses the WeatherViewModel and WeatherViewModelFactory. The ViewModel acts as a bridge between the UI and the model, handling UI-related data logic and transformations.

ui: Contains the screens and composables for the user interface, built using Jetpack Compose. It demonstrates how to display data fetched from the ViewModel.

database: (Not explicitly shown, but implied with Room usage) This would typically contain Room database configuration, entities, and DAOs (Data Access Objects) for local data persistence.

Features
MVVM Architecture: Utilizes the MVVM pattern for separation of concerns, making the code more modular and testable.
Retrofit: For network calls to fetch weather data from an external API.
Room Database: For storing weather data locally, allowing offline access to previously fetched data.
State Management: Uses MutableStateFlow and StateFlow for observing and reacting to data changes in the UI.
Jetpack Compose Navigation: Demonstrates simple navigation between screens in a Compose-based UI.
Techniques
Dependency Injection (Manual): While not using a DI framework like Hilt, the example manually injects dependencies into the WeatherViewModel via a factory, demonstrating the concept of dependency injection.
Asynchronous Programming: Uses Kotlin coroutines for making network calls and database operations without blocking the main thread.
JSON Parsing with Retrofit: The GsonConverterFactory is used to automatically convert JSON responses from the network into Kotlin data objects.
Composable Functions: Demonstrates how to build UIs with reusable Composable functions in Jetpack Compose.
Getting Started
To explore this project:

Clone the repository to your local machine.
Open the project in Android Studio.
Run the application on an emulator or physical device.
Key Concepts for Beginners
MVVM: Understand how the ViewModel separates UI logic from business logic, improving testability and maintainability.
StateFlow: Learn how to use StateFlow to build reactive UIs that respond to data changes.
Retrofit and Room: Explore how to fetch data from the internet and manage local databases in Android.
Conclusion
This project serves as a practical introduction to building Android applications with Kotlin, Jetpack Compose, and the MVVM architecture. It covers fundamental concepts like network requests, local data storage, reactive UI updates, and navigation between screens. By studying and experimenting with this example, students can gain a solid foundation in modern Android development practices.
