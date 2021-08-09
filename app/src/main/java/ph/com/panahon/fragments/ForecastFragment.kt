package ph.com.panahon.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ph.com.panahon.*
import java.time.LocalDate


class ForecastFragment : Fragment() {

    private var degreeUnitCode: Int = 0 //Initialize only to 0, 0 has no significance value.
    private lateinit var weather: Weather
    private lateinit var communicator: Communicator
    private var forecasts : ArrayList<Forecast> = ArrayList()

    // PLACEHOLDERS FOR API DATA
    private lateinit var location: String
    private var temperature: Int = 0
    private var weatherCode: Int = 0
    private var humidity: Int = 0
    private var precipitation: Int = 0

    /**
     * During onPause, the Communicator retrieves the current settings in the forecast fragment;
     * this will be used by the SettingsFragment to determine which settings are ticked or checked.
     */
    override fun onPause() {
        super.onPause()
        communicator = activity as Communicator
        communicator.storeUnitDegreePreference(degreeUnitCode)
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

        // Initialize the Weather for today
        this.initializeWeatherToday(view)

        // Initialize the Forecasts for the following days (RecyclerView)
        this.initializeForecasts(view)

        return view
    }

    private fun callAPI() {
        // Call the third-party API then describe the data by manipulating the Weather Class.
        // --> happens here <--

        // Store the data for today's weather (sample only)
        location = "Binan City"
        weatherCode = Weather.RAINY
        temperature = 32
        humidity = 30
        precipitation = 22

        // Store data for forecasts (sample only)
        forecasts.add(Forecast("Monday", Weather.RAINY, 24, 30, 34))
        forecasts.add(Forecast("Tuesday", Weather.SUNNY, 30, 30, 34))
        forecasts.add(Forecast("Wednesday", Weather.CLOUDY, 28, 30, 34))
        forecasts.add(Forecast("Thursday", Weather.SUNNY, 31, 30, 34))
        forecasts.add(Forecast("Friday", Weather.SUNNY, 32, 30, 34))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initializeWeatherToday(view: View) {

        weather = Weather(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            weather.setDate(LocalDate.now())
        }
        weather.setLocation(location)
        weather.setWeather(weatherCode)
        weather.setTemperature(temperature, Weather.C)
        weather.setHumidityPercentage(humidity)
        weather.setPrecipitationPercentage(precipitation)
        weather.setUnitDegree(degreeUnitCode)
        view.findViewById<TextView>(R.id.tv_temperature).setOnClickListener {
            degreeUnitCode = if (degreeUnitCode == Weather.C) {
                weather.setUnitDegree(Weather.F)
                Weather.F
            } else {
                weather.setUnitDegree(Weather.C)
                Weather.C
            }
        }
    }

    private fun initializeForecasts(view: View) {
        val rvForecasts : RecyclerView = view.findViewById(R.id.rv_forecast)!!
        val manager: RecyclerView.LayoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        rvForecasts.layoutManager = manager
        rvForecasts.adapter = ForecastAdapter(forecasts)

        val dividerItemDecoration = DividerItemDecoration(
            rvForecasts.context,
            LinearLayoutManager.HORIZONTAL
        )
        rvForecasts.addItemDecoration(dividerItemDecoration)
    }
}