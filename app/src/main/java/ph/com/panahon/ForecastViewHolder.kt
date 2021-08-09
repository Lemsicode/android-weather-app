package ph.com.panahon

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

open class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var ivWeather: ImageView = itemView.findViewById(R.id.iv_template_weather)
    var tvDay: TextView = itemView.findViewById(R.id.tv_template_day)
    var tvTemp: TextView = itemView.findViewById(R.id.tv_template_temp)
    var tvUnitDegree: TextView = itemView.findViewById(R.id.tv_template_unit_degree)
    var bg: ConstraintLayout = itemView.findViewById(R.id.cl_template)

    @RequiresApi(Build.VERSION_CODES.M)
    open fun setWeather(weatherCode: Int) {
        when (weatherCode) {
            Weather.SUNNY -> {
                ivWeather.setImageResource(R.drawable.ic_sun)
                bg.setBackgroundColor(itemView.resources.getColor(R.color.sunny_bg, itemView.context.theme))
            }
            Weather.CLOUDY -> {
                ivWeather.setImageResource(R.drawable.ic_cloud)
                bg.setBackgroundColor(itemView.resources.getColor(R.color.cloudy_bg, itemView.context.theme))
            }
            Weather.STORMY -> {
                ivWeather.setImageResource(R.drawable.ic_stormy)
                bg.setBackgroundColor(itemView.resources.getColor(R.color.storms_bg, itemView.context.theme))
            }
            Weather.SNOWY -> {
                ivWeather.setImageResource(R.drawable.ic_snow)
                bg.setBackgroundColor(itemView.resources.getColor(R.color.snow_bg, itemView.context.theme))
            }
            Weather.RAINY -> {
                ivWeather.setImageResource(R.drawable.ic_rain)
                bg.setBackgroundColor(itemView.resources.getColor(R.color.rainy_bg, itemView.context.theme))
            }
        }
    }
}