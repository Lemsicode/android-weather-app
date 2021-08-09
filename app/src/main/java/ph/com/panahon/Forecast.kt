package ph.com.panahon

open class Forecast (day: String, weatherCode: Int, temp: Int, humidityPercentage: Int, precipitationPercentage: Int) {
    var day: String = day
    var weather: Int = weatherCode
    var temperature: Int = temp
    var humidity: Int = humidityPercentage
    var precipitation: Int = precipitationPercentage
}