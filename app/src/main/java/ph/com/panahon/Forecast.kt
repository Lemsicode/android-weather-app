package ph.com.panahon

open class Forecast (day: String, location: String, weatherCode: Int, temp: Int, unitDegree: Int, humidityPercentage: Int, precipitationPercentage: Int) {
    var day: String = day
    var location: String = location
    var weather: Int = weatherCode
    var temperature: Int = temp
    var unitDegree: Int = unitDegree
    var humidity: Int = humidityPercentage
    var precipitation: Int = precipitationPercentage
}