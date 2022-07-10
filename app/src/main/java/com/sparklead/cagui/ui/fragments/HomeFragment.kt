package com.sparklead.cagui.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.sparklead.cagui.R
import com.sparklead.cagui.ui.activities.SettingActivity

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = activity!!.getColor(R.color.third_color)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val imageSlider = root.findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://picsum.photos/200/300","Hello"))
        imageList.add(SlideModel("https://picsum.photos/200/300","bye"))

        imageSlider.setImageList(imageList,ScaleTypes.FIT)

        return root
    }

}