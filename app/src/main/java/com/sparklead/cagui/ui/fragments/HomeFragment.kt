package com.sparklead.cagui.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.sparklead.cagui.R
import com.sparklead.cagui.models.GlideLoader
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.activities.SettingActivity
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var mUserDetails :User

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

//        val imageSlider = root.findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://picsum.photos/200/300","Hello"))
        imageList.add(SlideModel("https://picsum.photos/200/300","bye"))

//        imageSlider.setImageList(imageList,ScaleTypes.FIT)

        return root
    }

    private fun getUserDetails()
    {
        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().getUserDetailsForFragment(this)
    }

    fun userDetailsSuccess(user: User){

        mUserDetails = user

        hideProgressDialog()

        GlideLoader(activity!!).loadUserPicture(user.image,user_image_home)

        user_image_home.setOnClickListener {
            startActivity(Intent(context,SettingActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getUserDetails()
    }


}