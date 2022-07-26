package com.sparklead.cagui.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.ui.activities.ExploreDetailsActivity
import kotlinx.android.synthetic.main.fragment_explore.*


class ExploreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = activity!!.getColor(R.color.second_color)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root =inflater.inflate(R.layout.fragment_explore,container,false)

        val cv1= root.findViewById<CardView>(R.id.cv_accounting)
        val cv2= root.findViewById<CardView>(R.id.cv_aerospace)
        val cv3= root.findViewById<CardView>(R.id.cv_agriculture)
        val cv4= root.findViewById<CardView>(R.id.cv_aquaculture)

        cv1.setOnClickListener {
            val intent = Intent(context,ExploreDetailsActivity::class.java)
            val image = "android.resource://com.sparklead.cagui/drawable/account"
            intent.putExtra(Constants.EXTRA_IMAGE,image)
            intent.putExtra(Constants.EXTRA_TITLE,"Accounting")
            startActivity(intent)
        }
        cv2.setOnClickListener {
            val intent = Intent(context,ExploreDetailsActivity::class.java)
            val image = "android.resource://com.sparklead.cagui/drawable/aerospace"
            intent.putExtra(Constants.EXTRA_IMAGE,image)
            intent.putExtra(Constants.EXTRA_TITLE,"Aerospace")
            startActivity(intent)
        }
        cv3.setOnClickListener {
            val intent = Intent(context,ExploreDetailsActivity::class.java)
            val image = "android.resource://com.sparklead.cagui/drawable/agriculture"
            intent.putExtra(Constants.EXTRA_IMAGE,image)
            intent.putExtra(Constants.EXTRA_TITLE,"Agriculture")
            startActivity(intent)
        }
        cv4.setOnClickListener {
            val intent = Intent(context,ExploreDetailsActivity::class.java)
            val image = "android.resource://com.sparklead.cagui/drawable/aquaculture"
            intent.putExtra(Constants.EXTRA_IMAGE,image)
            intent.putExtra(Constants.EXTRA_TITLE,"Aquaculture")
            startActivity(intent)
        }
        return root
    }
}