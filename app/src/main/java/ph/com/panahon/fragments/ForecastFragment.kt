package ph.com.panahon.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.RequiresApi
import ph.com.panahon.Date
import ph.com.panahon.R
import ph.com.panahon.Weather

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForecastFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var weather: Weather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
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

        weather.changeDegree(Weather.C)             //Converting the View from F to C and vice versa
                                                    //Indicated by Weather.C or Weather.F
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForecastFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}