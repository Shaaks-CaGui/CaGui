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