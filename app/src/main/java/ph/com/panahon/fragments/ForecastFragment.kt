package ph.com.panahon.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import ph.com.panahon.*


class ForecastFragment : Fragment() {

    private var isCelsius: Boolean = true
    private lateinit var weather: Weather
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)

        isCelsius = arguments?.getBoolean(Keys.UNIT_DEGREE_KEY.name) == true
        communicator = activity as Communicator
        communicator.toggleCelsius(isCelsius)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weather = Weather(view)                     //creating an instance of Weather

        weather.changeWeather(Weather.CLOUDY)       //Changing the UI Elements
                                                    //Weather.CLOUDY, Weather.RAINY, Weather.SUNNY, Weather.SNOWY, Weather.STORMY

        weather.changeLocation("JPN")       //Changing Locations
        weather.changeTemp(101, Weather.F)    //Changing the Temperature
        weather.changeDate(Date(8, 9, 2021))    //Changing the Date (MM-DD-YYYY)

        if (isCelsius)
            weather.changeDegree(Weather.C)
        else
            weather.changeDegree(Weather.F)
    }
}