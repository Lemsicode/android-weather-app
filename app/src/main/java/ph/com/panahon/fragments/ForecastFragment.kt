package ph.com.panahon.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import ph.com.panahon.*


class ForecastFragment : Fragment() {

    private var degreeUnitCode: Int = 0 //Initialize only to 0, 0 has no significance value.
    private lateinit var weather: Weather
    private lateinit var communicator: Communicator

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
        this.callAPI(view)

        return view
    }

    /**
     * The Function that calls the API to retrieve necessary data to be reflected in the UI Elements
     *
     * Weather Class Methods:
     * setWeather(weatherCode : Int) - weatherCodes: Weather.CLOUDY, Weather.RAINY, Weather.SUNNY, etc.
     * setLocation(location : String)
     * setTemperature(temp : Int, degreeUnitCode : Int) - degreeUnitCodes: Weather.F and Weather.C
     * setDate(date : Date) where date is Date(month : Int, day : Int, year, Int)
     * setDegreeUnit(degreeUnitCode : Int) - degreeUnitCodes: Weather.F and Weather.C
     * setHumidityPercentage(percentage : Int) - no need to add %, just the integer
     *
     * @weather the Weather Class passed by onCreateView
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun callAPI(view: View) {

        // Create a Weather Class
        weather = Weather(view)

        // Call the third-party API then describe the data by manipulating the Weather Class.
        // --> happens here <--

        // Edit the Weather Class
        weather.setDate(Date(8, 9, 2021))
        weather.setLocation("MNL")
        weather.setWeather(Weather.STORMY)
        weather.setTemperature(32, Weather.C)
        weather.setHumidityPercentage(77)
        weather.setUnitDegree(degreeUnitCode)

        // Don't mind this, just a feature that when you click the Temperature it changes unit degree for preference.
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
}