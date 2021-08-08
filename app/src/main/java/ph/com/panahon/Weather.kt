package ph.com.panahon

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout


open class Weather(view: View) {

    private var theView = view
    private var bg : ConstraintLayout = view.findViewById(R.id.forecast_screen)
    private var ivWeather : ImageView = view.findViewById(R.id.iv_weather)
    private var tvTemperature : TextView = view.findViewById(R.id.tv_temperature)
    private var tvLocation : TextView = view.findViewById(R.id.tv_location)
    private var tvDate : TextView = view.findViewById(R.id.tv_date)
    private var tvWeatherDescription : TextView = view.findViewById(R.id.tv_weather_desc)
    private var tvDegree : TextView = view.findViewById(R.id.tv_temp_degree)

    private var fahrenheit : Int = 0
    private var celsius : Int = 0

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
     * @weather the integer that corresponds to the weather, all integers are given by the constants given by the class.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    open fun changeWeather(weather: Int) {
        when (weather) {
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
     * @temp the temperature provided
     * @degree an Integer that corresponds to the choice, choices are built in in the class.
     */
    open fun changeTemp(temp: Int, degree: Int) {
        when (degree) {
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

    /**
     * Changes the location in the UI
     * @location the location provided which is a String
     */
    open fun changeLocation(location: String) {
        tvLocation.text = location
    }

    /**
     * Changes the Date in the UI
     * @date a Java Date Object that is provided.
     */
    open fun changeDate(date: Date) {
        val actualDate = date.getDate()
        tvDate.text = actualDate
    }

    /**
     * Changes the Degree to either Fahrenheit or Celsius
     * @degree an Integer that corresponds to the choice, choices are built in in the class.
     */
    open fun changeDegree(degree: Int) {
        when (degree) {
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
        tvLocation.text = "IDK"
        tvDate.text = "The End of Time"
        tvTemperature.text = ":("
        tvDegree.text = ""
        bg.setBackgroundColor(theView.resources.getColor(R.color.teal_700, theView.context.theme))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeFontColor(color: Int) {
        when (color) {
            WHITE -> {
                tvTemperature.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvLocation.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvDate.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvWeatherDescription.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
                tvDegree.setTextColor(theView.resources.getColor(R.color.white, theView.context.theme))
            }
            DARK -> {
                tvTemperature.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvLocation.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvDate.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvWeatherDescription.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
                tvDegree.setTextColor(theView.resources.getColor(R.color.black, theView.context.theme))
            }
        }
    }
}