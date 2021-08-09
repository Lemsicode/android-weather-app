package ph.com.panahon

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class ForecastAdapter(private var forecasts: ArrayList<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.forecast_template, parent, false)

        return ForecastViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.setWeather(forecasts[position].weather)
        holder.tvDay.text = forecasts[position].day
        holder.tvTemp.text = forecasts[position].temperature.toString()
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }
}