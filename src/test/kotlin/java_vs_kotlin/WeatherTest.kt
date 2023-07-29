package java_vs_kotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Color

class WeatherTest {
    @Test
    fun shouldReturnCorrectWeatherDataUponCreation() {
        val initDesc = "Unknown"
        val initColor = Color.BLACK
        val weather = Weather(initDesc, initColor)
        assertEquals(initDesc, weather.description)
        assertEquals(initColor, weather.color)
    }
    @Test
    fun shouldReturnWarmAndBlueUponUpdateTo25Degrees() {
        val initDesc = "Unknown"
        val initColor = Color.BLACK
        val weather = Weather(initDesc, initColor)
        weather.updateWeather(25)//Warm and Blue
        assertEquals("Warm", weather.description)
        assertEquals(Color.BLUE, weather.color)
    }
}