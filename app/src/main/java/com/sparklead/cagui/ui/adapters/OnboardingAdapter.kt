package com.sparklead.cagui.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.cagui.R
import com.sparklead.cagui.models.OnboardingItem

class OnboardingAdapter(
    private val onboardingItems: List<OnboardingItem>
    ) :RecyclerView.Adapter<OnboardingAdapter.OnboardingItemViewHolder>()
{

    inner class OnboardingItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageOnBoarding)
        private val textTitle = view.findViewById<TextView>(R.id.text_title)
        private val textDescription = view.findViewById<TextView>(R.id.text_description)

        fun bind(onboardingItems: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItems.onboardingImage)
            textTitle.text = onboardingItems.title
            textDescription.text = onboardingItems.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

}