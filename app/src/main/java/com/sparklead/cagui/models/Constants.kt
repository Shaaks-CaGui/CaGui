package com.sparklead.cagui.models

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {

    var EXTRA_TITLE : String = "title"
    const val USERS :String = "users"


    const val CAGUI_PREFERENCES : String = "caguiPrefs"
    const val LOGGED_IN_USERNAME : String = "logged_in_user"
    const val EXTRA_USER_DETAILS : String = "extra_user_details"

    const val PICK_IMAGE_REQUEST_CODE = 1
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val NAME: String = "name"
    const val MALE : String = "Male"
    const val FEMALE : String = "Female"
    const val MOBILE : String = "phone"
    const val GENDER : String = "gender"
    const val CURRENT_CLASS : String = "currentClass"
    const val STREAM : String = "stream"
    const val INTERESTS :String = "interests"
    const val IMAGE :String ="image"
    const val PROFILE_COMPLETED :String ="profileCompleted"
    const val USER_PROFILE_IMAGE :String = "user_profile_image"

    fun showImageChooser(activity : Activity)
    {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?):String?
    {

        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))

    }


}