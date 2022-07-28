package com.sparklead.cagui.ui.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sparklead.cagui.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        val linear = findViewById<LinearLayout>(R.id.background_splash_screen)
        val animationDrawable = linear.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        },3000)
    }

}