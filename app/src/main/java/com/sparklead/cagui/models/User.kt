package com.sparklead.cagui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    val id: String = "",
    val name : String = "",
    val email :String ="",
    val userType :String = "",
    val phone :String = "",
    val profileCompleted:Int = 0

):Parcelable