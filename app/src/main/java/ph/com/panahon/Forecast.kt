package ph.com.panahon

open class Forecast (day: String, location: String, weatherCode: Int, cels: Int, fah: Int, humidityPercentage: Int, precipitationPercentage: Int) {
    var day: String = day
    var location: String = location
    var weather: Int = weatherCode
    var celsius: Int = cels
    var fahrenheit: Int = fah
    var humidity: Int = humidityPercentage
    var precipitation: Int = precipitationPercentage
}