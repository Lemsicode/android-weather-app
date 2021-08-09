package ph.com.panahon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import ph.com.panahon.Communicator
import ph.com.panahon.Keys
import ph.com.panahon.R
import ph.com.panahon.Weather

class SettingsFragment : Fragment() {

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Retrieve the Data from ForecastFragment
        val degreeUnitCode : Int = arguments?.getInt(Keys.UNIT_DEGREE_KEY.name)!!

        // If Data shows Degree Unit is Celsius, check the switch; otherwise, unchecked
        view.findViewById<SwitchCompat>(R.id.switch_unit).isChecked = degreeUnitCode == Weather.C

        // Add a Listener to the Switch so that in each turn, it stores the Data for ForecastFragment to check later
        communicator = activity as Communicator
        view.findViewById<SwitchCompat>(R.id.switch_unit).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                communicator.storeUnitDegreePreference(Weather.C)
            else
                communicator.storeUnitDegreePreference(Weather.F)
        }

        return view
    }
}