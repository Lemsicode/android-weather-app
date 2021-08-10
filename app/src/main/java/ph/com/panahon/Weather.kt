package ph.com.panahon

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import java.time.LocalDate
import java.util.*


open class Weather(view: View) {

    private var theView = view
    private var bg : ConstraintLayout = view.findViewById(R.id.forecast_screen)

    private var ivWeather : ImageView = view.findViewById(R.id.iv_weather)
    private var ivPrecipitationLogo : ImageView = view.findViewById(R.id.iv_chances_rain)
    private var ivHumidityLogo : ImageView = view.findViewById(R.id.iv_humidity)

    private var tvTemperature : TextView = view.findViewById(R.id.tv_temperature)
    private var tvLocation : TextView = view.findViewById(R.id.tv_location)
    private var tvWeatherDescription : TextView = view.findViewById(R.id.tv_weather_desc)
    private var tvDegree : TextView = view.findViewById(R.id.tv_temp_degree)
    private var tvHumidity : TextView = view.findViewById(R.id.tv_humidity)
    private var tvTodayTitle : TextView = view.findViewById(R.id.tv_today)
    private var tvPrecipitation : TextView = view.findViewById(R.id.tv_precipitation)

    private var location : String = "Manila"
    private var day : String = "Monday"

    private var fahrenheit : Int = 0
    private var celsius : Int = 0
    private var weatherCode : Int = SUNNY

    companion object {
        const val SUNNY = 1
        const val RAINY = 2
        const val CLOUDY = 3
        const val SNOWY = 4
        const val STORMY = 5

        const val F = 100
        const val C = 101

        const val WHITE = 200
        const val DARK = 201
    }

    /**
     * The Method that changes the UI to the right weather
     * @param weatherCode the integer that corresponds to the weather, all integers are given by the constants given by the class.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    open fun setWeather(weatherCode: Int) {
        this.weatherCode = weatherCode
        when (weatherCode) {
            SUNNY -> {
                sunnyWeather()
            }
            RAINY -> {
                rainyWeather()
            }
            CLOUDY -> {
                cloudyWeather()
            }
            SNOWY -> {
                snowyWeather()
            }
            STORMY -> {
                stormyWeather()
            }
            else -> {
                unknownWeather()
            }
        }
    }

    /**
     * Changes the temperature in the UI
     * @param temp the temperature provided
     * @param degreeUnitCode an Integer that corresponds to the choice, choices are built in in the class.
     */
    open fun setTemperature(temp: Int, degreeUnitCode: Int) {
        if(validWeatherCode()) {
            when (degreeUnitCode) {
            F -> {
                fahrenheit = temp
                celsius = (fahrenheit - 32) * 5/9

                tvTemperature.text = fahrenheit.toString()
                tvDegree.text = "°F"
            }
            C -> {
                celsius = temp
                fahrenheit = (celsius * 9/5) + 32

                tvTemperature.text = celsius.toString()
                tvDegree.text = "°C"
            }
        }
        }
    }

    /**
     * Changes the location in the UI
     * @param location the location provided which is a String
     */
    open fun setLocation(location: String) {
        if(validWeatherCode()) {
            this.location = location
        }
    }

    /**
     * Changes the Date in the UI
     * @param localDate a Java Date Object that is provided.
     */

    @RequiresApi(Build.VERSION_CODES.O)
    open fun setDate(localDate: LocalDate) {
        if(validWeatherCode()) {
            this.day = localDate.dayOfWeek.name.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }

    /**
     * Changes the Unit Degree to either Fahrenheit or Celsius
     * @param degreeUnitCode an Integer that corresponds to the choice, choices are built in in the class.
     */
    open fun setUnitDegree(degreeUnitCode: Int) {
        if(validWeatherCode()) {
            when (degreeUnitCode) {
                F -> {
                    tvTemperature.text = fahrenheit.toString()
                    tvDegree.text = "°F"
                }
                C -> {
                    tvTemperature.text = celsius.toString()
                    tvDegree.text = "°C"
                }
                else -> {
                    tvTemperature.text = celsius.toString()
                    tvDegree.text = "°C"
                }
            }
        }
    }

    /**
     * Sets the Humidity. doesn't need a percentage sign.
     *
     * @param percentage the percentage input
     */
    open fun setHumidityPercentage(percentage: Int) {
        if(validWeatherCode()) {
            val humidity = ": $percentage%"
            tvHumidity.text = humidity
        }
    }

    /**
     * Sets the Precipitation. doesn't need a percentage sign.
     *
     * @param percentage the percentage input
     */
    open fun setPrecipitationPercentage(percentage: Int) {
        if(validWeatherCode()) {
            val precipitation = ": $percentage%"
            tvPrecipitation.text = precipitation
        }
    }

    private fun validWeatherCode() : Boolean  {
        return weatherCode in 1..5
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun cloudyWeather() {
        ivWeather.setImageResource(R.drawable.ic_cloud)
        tvWeatherDescription.text = "Cloudy"
        bg.setBackgroundColor(theView.resources.getColor(R.color.cloudy_bg, theView.context.theme))
        changeFontColor(WHITE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun sunnyWeather() {
        ivWeather.setImageResource(R.drawable.ic_sun)
        tvWeatherDescription.text = "Sunny"
        bg.setBackgroundColor(theView.resources.getColor(R.color.sunny_bg, theView.context.theme))
        changeFontColor(DARK)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun rainyWeather() {
        ivWeather.setImageResource(R.drawable.ic_rain)
        tvWeatherDescription.text = "Rainy"
        bg.setBackgroundColor(theView.resources.getColor(R.color.rainy_bg, theView.context.theme))
        changeFontColor(WHITE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun snowyWeather() {
        ivWeather.setImageResource(R.drawable.ic_snow)
        tvWeatherDescription.text = "Snowy"
        bg.setBackgroundColor(theView.resources.getColor(R.color.snow_bg, theView.context.theme))
        changeFontColor(DARK)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun stormyWeather() {
        ivWeather.setImageResource(R.drawable.ic_stormy)
        tvWeatherDescription.text = "Stormy"
        bg.setBackgroundColor(theView.resources.getColor(R.color.storms_bg, theView.context.theme))
        changeFontColor(WHITE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    private fun unknownWeather() {
        ivWeather.setImageResource(android.R.color.transparent)
        tvWeatherDescription.text = "Unknown"
        tvLocation.text = ""
        tvTemperature.text = ":("
        tvDegree.text = ""
        tvHumidity.text = ":IDK"
        tvPrecipitation.text = ":IDK"
        bg.setBackgroundColor(theView.resources.getColor(R.color.teal_700, theView.context.theme))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeFontColor(color: Int) {
        when (color) {
            WHITE -> {
                tvTemperature.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvLocation.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvWeatherDescription.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvDegree.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvHumidity.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvTodayTitle.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvPrecipitation.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                ivPrecipitationLogo.setImageResource(R.drawable.ic_precip_white)
                ivHumidityLogo.setImageResource(R.drawable.ic_humidity_white)
            }
            DARK -> {
                tvTemperature.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvLocation.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvWeatherDescription.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvDegree.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvHumidity.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvTodayTitle.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvPrecipitation.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                ivPrecipitationLogo.setImageResource(R.drawable.ic_precip_black)
                ivHumidityLogo.setImageResource(R.drawable.ic_humidity_black)
            }
        }
    }
}