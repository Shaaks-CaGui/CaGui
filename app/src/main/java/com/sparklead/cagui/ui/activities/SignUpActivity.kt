package com.sparklead.cagui.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.cagui.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

    }

}