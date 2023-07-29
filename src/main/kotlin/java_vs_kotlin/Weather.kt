package java_vs_kotlin

import java.awt.Color

data class Weather(var description: String, var color: Color) {
    fun updateWeather(degrees: Int) {
        val (localDescription, localColor) =
            when {
                degrees < 10 -> "Cold" to Color.WHITE
                degrees < 20 -> "Normal" to Color.GRAY
                degrees < 30 -> "Warm" to Color.BLUE
                else -> "Hot" to Color.ORANGE
            }
        description = localDescription
        color = localColor
    }
}