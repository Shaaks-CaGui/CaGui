package com.sparklead.cagui.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import kotlinx.android.synthetic.main.activity_explore_details.*

class ExploreDetailsActivity : AppCompatActivity() {

    lateinit var mTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_details)

        supportActionBar?.hide()

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)!!

        tv_title_explore_details.text = mTitle
        tv_title_heading.text = mTitle
    }

}