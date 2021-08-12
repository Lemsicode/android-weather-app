package ph.com.panahon.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ph.com.panahon.*
import java.time.LocalDate


class ForecastFragment : Fragment() {

    private var degreeUnitCode: Int = 0 //Initialize only to 0, 0 has no significance value.
    private lateinit var weather: Weather
    private lateinit var communicator: Communicator
    private lateinit var rvForecasts : RecyclerView

    // STORAGE FOR API DATA
    private var forecasts : ArrayList<Forecast> = ArrayList()

    /**
     * During onPause, the Communicator retrieves the current settings in the forecast fragment;
     * this will be used by the SettingsFragment to determine which settings are ticked or checked.
     */
    override fun onPause() {
        super.onPause()
        communicator = activity as Communicator
        communicator.storeUnitDegreePreference(weather.degreeUnitCode)
    }

    /**
     * The Usual onCreateView that every Activity or Fragment should have.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get the weatherCode first from SettingsFragment if there's any.
        degreeUnitCode = arguments?.getInt(Keys.UNIT_DEGREE_KEY.name, Weather.C)!!

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)

        // Call third-party API for weather
        this.callAPI()

        // Initialize the Forecasts for the following days (RecyclerView)
        this.initializeForecasts(view)

        // Initialize the Weather for today
        this.initializeWeatherToday()

        // Return View
        return view
    }

    /**
     * The most important function in the program.
     */
    private fun callAPI() {
        // Call the third-party API
        // --> happens here <--






        // Declare the location
        val location = "Metro Manila"

        // FORMAT: addForecast( day, location, weatherCode, temperature, unitDegree, humidity, precipitation)

        // The first element in the ArrayList is the forecast for "Today"
        addForecast("Wednesday", location, Weather.SUNNY, 35, Weather.C, 30, 19)

        // The rest of the elements are the forecasts for the following days
        addForecast("Thursday", location, Weather.CLOUDY, 29, Weather.C,32, 14)
        addForecast("Friday", location, Weather.SNOWY, 13, Weather.C,30, 3)
        addForecast("Saturday", location, Weather.RAINY, 27, Weather.C,30, 77)
        addForecast("Sunday", location, Weather.STORMY, 26, Weather.C,30, 77)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initializeWeatherToday() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            weather.setDate(LocalDate.now())
        }
        weather.setLocation(forecasts[0].location)
        weather.setWeather(forecasts[0].weather)
        weather.setTemperature(forecasts[0].celsius, Weather.C)
        weather.setHumidityPercentage(forecasts[0].humidity)
        weather.setPrecipitationPercentage(forecasts[0].precipitation)
        weather.setUnitDegree(degreeUnitCode)
    }

    private fun initializeForecasts(view: View) {
        rvForecasts = view.findViewById(R.id.rv_forecast)!!
        val manager: RecyclerView.LayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        weather = Weather(view)
        rvForecasts.layoutManager = manager
        rvForecasts.adapter = ForecastAdapter(forecasts, weather)
    }

    private fun addForecast(day: String, location: String, weatherCode: Int, temperature: Int, unitDegree: Int, humidityPercentage: Int, precipitationPercentage: Int){

        val celsius: Int
        val fahrenheit: Int

        if(unitDegree == Weather.C) {
            celsius = temperature
            fahrenheit = (celsius * 9/5) + 32
        }
        else {
            fahrenheit = temperature
            celsius = (fahrenheit - 32) * 5/9
        }

        forecasts.add(Forecast(day, location, weatherCode, celsius, fahrenheit, humidityPercentage, precipitationPercentage))
    }
}