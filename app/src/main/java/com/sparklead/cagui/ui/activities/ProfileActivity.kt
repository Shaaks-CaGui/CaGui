package com.sparklead.cagui.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.cagui.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
    }
}