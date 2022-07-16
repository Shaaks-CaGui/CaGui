package com.sparklead.cagui.models

import android.os.Parcelable
import com.sparklead.cagui.ui.activities.ExploreDetailsActivity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BranchItem(
    val no : String = " ",
    val details : String = " ",
    val branch1 : Info = Info(),
    val branch2 : Info = Info(),
    val branch3 : Info = Info()
) : Parcelable
