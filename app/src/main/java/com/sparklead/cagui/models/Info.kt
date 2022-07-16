package com.sparklead.cagui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    val overview :String = "",
    val path : String = "",
    val responsibilities :String = "",
    val skills : String = "",
    val careerProspects :String= "",
    val companies :String= "",
    val prosAndCons:String = "",
    val futureGrowth :String = "",
    val alternateCareer :String = "",
    val averageSalary :String = "",
    val title :String = "",
    val tag :String = ""
) : Parcelable {
}