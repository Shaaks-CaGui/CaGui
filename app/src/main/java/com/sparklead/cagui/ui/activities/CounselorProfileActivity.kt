package com.sparklead.cagui.ui.activities

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.GlideLoader
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_counselor_profile.*
import kotlinx.android.synthetic.main.activity_profile.*

class CounselorProfileActivity : BaseActivity() , View.OnClickListener {

    private lateinit var mUserDetails : User
    private var mSelectedImageFileUrl : Uri? =null
    private var mUserProfileImageURl :String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_profile)

        supportActionBar?.hide()



        et_name_counselor_profile.setText(mUserDetails.name)

        if(mUserDetails.profileCompleted==0)
        {
            title_counselor_profile.text = "Complete Profile"
            et_name_counselor_profile.isEnabled = true

        }else{

            title_counselor_profile.text = "Edit Profile"
            GlideLoader(this).loadUserPicture(mUserDetails.image,user_counselor_image)

            if(mUserDetails.phone != 0L)
            {
                et_phoneNo_profile.setText(mUserDetails.phone.toString())
            }
            if(mUserDetails.gender== Constants.MALE)
            {
                rb_counselor_male.isChecked = true
            }
            else
            {
                rb_counselor_female.isChecked = true
            }

        }

        user_counselor_image.setOnClickListener(this)
        btn_counselor_done.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v!=null)
            when(v.id){

                R.id.user_counselor_image-> {

                    // Here we will check if the permission is already allowed or we need to request for it.
                    // First of all we will check the READ_EXTERNAL_STORAGE permission and if it is not allowed we will request for the same.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this)
//                        showErrorSnackBar("You already have the storage permission.", false)
                    } else {

                        /*Requests permissions to be granted to this application. These permissions
                         must be requested in your manifest, they should not be granted to your app,
                         and they should have protection level*/

                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE_COUNSELOR
                        )
                    }
                }
                R.id.btn_counselor_done ->
                {
                    if(validateUserProfileDetails())
                    {

                        showProgressbar(resources.getString(R.string.please_wait))

                        if(mSelectedImageFileUrl!=null)
                            FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUrl,Constants.USER_PROFILE_IMAGE_COUNSELOR)
                        else
                        {
                            updateUserProfileDetails()
                        }


                    }
                }
            }

    }

    private fun updateUserProfileDetails()
    {
        val userHashMap = HashMap<String,Any>()

        val name = et_name_counselor_profile.text.toString().trim{ it <= ' '}

        if(name != mUserDetails.name)
        {
            userHashMap[Constants.NAME_COUNSELOR]=name
        }

        val mobileNo = et_phoneNo_counselor_profile.text.toString().trim{ it <= ' '}
        val linkedin = et_linkedin_counselor_profile.text.trim{it <= ' '}
        val resume = et_resume_counselor_profile.text.toString().trim { it<=' '}
        val expert = et_expert_counselor_profile.text.toString().trim { it <= ' '}

        val gender = if(rb_counselor_male.isChecked)
        {
            Constants.MALE_COUNSELOR
        }
        else
        {
            Constants.FEMALE_COUNSELOR
        }

        if(mUserProfileImageURl.isNotEmpty()){
            userHashMap[Constants.IMAGE_COUNSELOR] = mUserProfileImageURl
        }
        if(mobileNo.isNotEmpty() && mobileNo!=mUserDetails.phone.toString())
        {
            userHashMap[Constants.MOBILE] = mobileNo.toLong()
        }

        if(linkedin.isNotEmpty() && linkedin != mUserDetails.currentClass.toString()){
            userHashMap[Constants.CURRENT_CLASS] = linkedin.toString()
        }

        if(resume.isNotEmpty() && resume !=mUserDetails.stream){
            userHashMap[Constants.STREAM] = resume
        }

        if(expert.isNotEmpty() && expert != mUserDetails.interests){
            userHashMap[Constants.INTERESTS] = expert
        }

        if(gender.isNotEmpty() && gender!=mUserDetails.gender)
        {
            userHashMap[Constants.GENDER] = gender
        }

        userHashMap[Constants.PROFILE_COMPLETED] = 1

//        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().updateUserProfileData(this@ProfileActivity,userHashMap)

//      showErrorSnackBar("your details are valid .",false)

    }
}