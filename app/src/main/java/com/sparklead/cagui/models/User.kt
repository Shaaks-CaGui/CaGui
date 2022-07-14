package com.sparklead.cagui.models

import android.media.Image
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    val id: String = " ",
    val name : String = " ",
    val email :String =" ",
    val userType :String =" ",
    val image: String =" ",
    val phone :Long = 0L,
    val currentClass : String = " " ,
    val stream :String= " ",
    val interests : String = " ",
    val gender: String = " ",
    val profileCompleted:Int = 0

):Parcelable