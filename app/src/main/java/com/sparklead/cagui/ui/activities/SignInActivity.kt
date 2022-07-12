package com.sparklead.cagui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.cagui.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()


        tv_signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        tv_forget_password.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
        }
        btn_signIn.setOnClickListener {
            startActivity(Intent(this,IntroActivity::class.java))
        }

    }

}