package ph.com.panahon

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

open class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private var ivWeather: ImageView = itemView.findViewById(R.id.iv_template_weather)
    private var tvTemp: TextView = itemView.findViewById(R.id.tv_template_temp)
    private var tvUnitDegree: TextView = itemView.findViewById(R.id.tv_template_unit_degree)
    var bg: LinearLayout = itemView.findViewById(R.id.ll_template)
    private var tvDay: TextView = itemView.findViewById(R.id.tv_template_day)

    lateinit var today: String
    lateinit var location: String
    var weatherCode: Int = 0
    var celsius: Int = 0
    var fahrenheit: Int = 0
    private var degreeUnit: Int = 0
    var humidityPercentage: Int = 0
    var precipitationPercentage: Int = 0

    open fun setDay(day: String) {
        tvDay.text = day
        this.today = day
    }

    open fun setTemperature(temp: Int, degreeUnitCode: Int) {
        degreeUnit = degreeUnitCode
        when (degreeUnitCode) {
            Weather.F -> {
                fahrenheit = temp
                celsius = (fahrenheit - 32) * 5/9

                tvTemp.text = fahrenheit.toString()
                tvUnitDegree.text = "°F"
            }
            Weather.C -> {
                celsius = temp
                fahrenheit = (celsius * 9/5) + 32

                tvTemp.text = celsius.toString()
                tvUnitDegree.text = "°C"
            }
        }
    }

    open fun setUnitDegree(unitDegreeCode: Int){
        if(unitDegreeCode == Weather.F) {
            tvTemp.text = fahrenheit.toString()
            tvUnitDegree.text = "°F"
        }
        else {
            tvTemp.text = celsius.toString()
            tvUnitDegree.text = "°C"
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    open fun setWeather(weatherCode: Int) {
        this.weatherCode = weatherCode
        when (weatherCode) {
            Weather.SUNNY -> {
                ivWeather.setImageResource(R.drawable.ic_sun)
                bg.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.rvi_sunny_corners, itemView.context.theme)
                changeFontColor(DARK)
            }
            Weather.CLOUDY -> {
                ivWeather.setImageResource(R.drawable.ic_cloud)
                bg.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.rvi_cloudy_corners, itemView.context.theme)
                changeFontColor(WHITE)
            }
            Weather.STORMY -> {
                ivWeather.setImageResource(R.drawable.ic_stormy)
                bg.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.rvi_stormy_corners, itemView.context.theme)
                changeFontColor(WHITE)
            }
            Weather.SNOWY -> {
                ivWeather.setImageResource(R.drawable.ic_snow)
                bg.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.rvi_snowy_corners, itemView.context.theme)
                changeFontColor(DARK)
            }
            Weather.RAINY -> {
                ivWeather.setImageResource(R.drawable.ic_rain)
                bg.background = ResourcesCompat.getDrawable(itemView.resources, R.drawable.rvi_rainy_corners, itemView.context.theme)
                changeFontColor(WHITE)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun changeFontColor(color: Int) {
        when (color) {
            WHITE -> {
                tvDay.setTextColor(itemView.resources.getColor(R.color.white, itemView.context.theme))
                tvTemp.setTextColor(itemView.resources.getColor(R.color.white, itemView.context.theme))
                tvUnitDegree.setTextColor(itemView.resources.getColor(R.color.white, itemView.context.theme))
            }
            DARK -> {
                tvDay.setTextColor(itemView.resources.getColor(R.color.black, itemView.context.theme))
                tvTemp.setTextColor(itemView.resources.getColor(R.color.black, itemView.context.theme))
                tvUnitDegree.setTextColor(itemView.resources.getColor(R.color.black, itemView.context.theme))
            }
        }
    }

    companion object {
        const val WHITE = 200
        const val DARK = 201
    }
}