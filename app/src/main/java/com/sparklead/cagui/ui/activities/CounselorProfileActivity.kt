package com.sparklead.cagui.ui.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.models.GlideLoader
import com.sparklead.cagui.models.User
import com.sparklead.cagui.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_counselor_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.IOException

class CounselorProfileActivity : BaseActivity() , View.OnClickListener {

    private lateinit var mUserDetails : User
    private var mSelectedImageFileUrl : Uri? =null
    private var mUserProfileImageURl :String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_profile)

        supportActionBar?.hide()

        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS))
        {
            //get the user_id details from intent as parcelableExtra
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }


        et_name_counselor_profile.setText(mUserDetails.name)

        if(mUserDetails.profileCompleted==0)
        {
            title_counselor_profile.text = "Complete Profile"
            et_name_counselor_profile.isEnabled = true
            iv_select_counselor_profile.setImageDrawable(resources.getDrawable(R.drawable.add_photo))
        }else{

            title_counselor_profile.text = "Edit Profile"
            GlideLoader(this).loadUserPicture(mUserDetails.image,user_counselor_image)

            iv_select_counselor_profile.setImageDrawable(resources.getDrawable(R.drawable.edit_icon))


            if(mUserDetails.phone != 0L)
            {
                et_phoneNo_profile.setText(mUserDetails.phone.toString())
            }
            if(mUserDetails.linkedin.isNotEmpty())
            {
                et_linkedin_counselor_profile.setText(mUserDetails.linkedin)
            }
            if(mUserDetails.resume.isNotEmpty())
            {
                et_resume_counselor_profile.setText(mUserDetails.resume)
            }
            if(mUserDetails.fieldExpert.isNotEmpty())
            {
                et_expert_counselor_profile.setText(mUserDetails.fieldExpert)
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
        iv_select_profile.setOnClickListener(this)
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
                R.id.iv_select_counselor_profile-> {

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
            userHashMap[Constants.MOBILE_COUNSELOR] = mobileNo.toLong()
        }

        if(linkedin.isNotEmpty() && linkedin != mUserDetails.linkedin){
            userHashMap[Constants.LINKEDIN_COUNSELOR] = linkedin.toString()
        }

        if(resume.isNotEmpty() && resume !=mUserDetails.resume){
            userHashMap[Constants.RESUME_COUNSELOR] = resume
        }

        if(expert.isNotEmpty() && expert != mUserDetails.fieldExpert){
            userHashMap[Constants.EXPERT_COUNSELOR] = expert
        }

        if(gender.isNotEmpty() && gender!=mUserDetails.gender)
        {
            userHashMap[Constants.GENDER_COUNSELOR] = gender
        }

        userHashMap[Constants.PROFILE_COMPLETED_COUNSELOR] = 1

//        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().updateUserProfileData(this@CounselorProfileActivity,userHashMap)

//      showErrorSnackBar("your details are valid .",false)

    }

    fun userProfileUpdateSuccess(){
        hideProgressDialog()

        showErrorSnackBar("profile updated successfully",false)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE_COUNSELOR) {
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this)
//                showErrorSnackBar("The storage permission is granted.", false)
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(
                    this,"Oops , you just denied the permission for storage .you can also allow in app permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE_COUNSELOR) {
                if (data != null) {
                    try {
                        //the uri of selected image from phone storage.
                        mSelectedImageFileUrl = data.data!!
//                        user_image.setImageURI(selectedImageFileUri)
                        GlideLoader(this).loadUserPicture(mSelectedImageFileUrl!!, user_counselor_image)

                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Image selection Failed !", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        else if (resultCode== Activity.RESULT_CANCELED)
        {
            Log.e("request cancelled", "image selection  cancelled")
        }
    }

    private fun validateUserProfileDetails():Boolean {
        return when{
            TextUtils.isEmpty(et_phoneNo_counselor_profile.text.toString().trim{it <=' '})->
            {
                showErrorSnackBar("Enter your mobile number",true)
                false
            }
            TextUtils.isEmpty(et_linkedin_counselor_profile.text.toString().trim{it <=' '})->
            {
                showErrorSnackBar("Enter your Linkedin profile",true)
                false
            }
            TextUtils.isEmpty(et_resume_counselor_profile.text.toString().trim{it <=' '})->
            {
                showErrorSnackBar("Enter your resume link",true)
                false
            }
            else->
            {
                true
            }
        }
    }

    fun imageUploadSuccess(imageUrl:String){

        hideProgressDialog()

        mUserProfileImageURl = imageUrl

        println("Yes image upload successfully")

        updateUserProfileDetails()
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}