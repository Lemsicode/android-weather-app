package ph.com.panahon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ph.com.panahon.fragments.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(ForecastFragment())    //Load the Forecast Fragment as Default Fragment

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_forecast -> {  //Forecast Fragment
                    loadFragment(ForecastFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.nav_search -> {    //Search for other locations Fragment
                    loadFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.nav_settings -> {  //Settings Fragment
                    loadFragment(SettingsFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {   //Default is Forecast Fragment
                    loadFragment(ForecastFragment())
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    /**
     * This method loads the fragment being passed into the parameter.
     */
    private fun loadFragment(fragment : Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment_holder, fragment)
        fragmentTransaction.commit()
    }
}