package com.example.assignmentmvvm.model

import androidx.room.*

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weatherCondition: String
)

@Dao
interface WeatherDao {
    @Insert
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM WeatherEntity ORDER BY id DESC LIMIT 1")
    suspend fun getLastWeather(): WeatherEntity?
}

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
