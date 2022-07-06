package com.sparklead.cagui.ui.activities

import android.app.PendingIntent.getActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.sparklead.cagui.R
import com.sparklead.cagui.ui.fragments.AdmissionFragment
import com.sparklead.cagui.ui.fragments.ExploreFragment
import com.sparklead.cagui.ui.fragments.FAQsFragment
import com.sparklead.cagui.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        openMainFragment()
        supportActionBar?.hide()

        nav_view.setItemSelected(R.id.navigation_home)

        nav_view.setOnItemSelectedListener {
            when (it) {

                R.id.navigation_home -> {
                    openMainFragment()
                }
                R.id.navigation_explore -> {
                    val exploreFragment = ExploreFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_dashboard, exploreFragment).commit()
                }
                R.id.navigation_admissions -> {
                    val admissionFragment = AdmissionFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_dashboard, admissionFragment).commit()

                    }
                R.id.navigation_faqs -> {
                    val faqsFragment = FAQsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_dashboard, faqsFragment).commit()
                    supportFragmentManager.popBackStack()
                    }
            }
        }
    }

    private fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_dashboard, fragment)
        transaction.commit()
    }
}