package com.sparklead.cagui.models

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sparklead.cagui.R
import java.io.IOException

class GlideLoader(val context: Context) {

    fun loadUserPicture(image:Any, imageView: ImageView)
    {
        try
        {
            Glide
                .with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .into(imageView)
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
    }
    fun loadProductPicture(image :Any, imageView: ImageView)
    {
        try
        {
            Glide
                .with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .into(imageView)
        }
        catch (e: IOException)
        {
            e.printStackTrace()
        }
    }
}