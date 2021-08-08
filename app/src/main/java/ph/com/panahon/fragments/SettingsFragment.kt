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

class SettingsFragment : Fragment() {

    private var isCelsius: Boolean = true
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        isCelsius = arguments?.getBoolean(Keys.UNIT_DEGREE_KEY.name) == true
        view.findViewById<SwitchCompat>(R.id.switch_unit).isChecked = isCelsius

        communicator = activity as Communicator
        view.findViewById<SwitchCompat>(R.id.switch_unit).setOnCheckedChangeListener { _, isChecked ->
            communicator.toggleCelsius(isChecked)
        }
        return view
    }
}