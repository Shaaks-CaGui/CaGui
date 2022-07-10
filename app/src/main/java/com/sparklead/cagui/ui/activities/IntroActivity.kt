package com.sparklead.cagui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.sparklead.cagui.R
import com.sparklead.cagui.models.OnboardingItem
import com.sparklead.cagui.ui.adapters.OnboardingAdapter
import kotlinx.android.synthetic.main.activity_intro.*
import java.text.FieldPosition

class IntroActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter : OnboardingAdapter
    private lateinit var indicatorContainer : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        supportActionBar?.hide()
        setOnboardingItem()
        setupIndicator()
        setCurrentIndicator(0)

        btn_getStarted.setOnClickListener{
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }
        tv_skip.setOnClickListener{
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }

    }


    private fun setOnboardingItem(){

        onboardingAdapter = OnboardingAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.test1,
                    title = "Intro Title  1",
                    description = "Intro description 1"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.test2,
                    title = "Intro Title  2",
                    description = "Intro description 2"
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.test3,
                    title = "Intro Title  3",
                    description = "Intro description 3"
                )
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onboardingViewPager.adapter = onboardingAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem+1 < onboardingAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }
            else{

            }
        }
    }

    private fun setupIndicator(){
        indicatorContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams :LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorContainer.childCount
        if(position==2){
            btn_getStarted.visibility = View.VISIBLE
            tv_skip.visibility = View.GONE
            imageNext.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.ic_baseline_keyboard_arrow_down_24))
        }
        else
        {
            btn_getStarted.visibility = View.GONE
            tv_skip.visibility = View.VISIBLE
            imageNext.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.ic_baseline_chevron_right_24))
        }
        for(i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}