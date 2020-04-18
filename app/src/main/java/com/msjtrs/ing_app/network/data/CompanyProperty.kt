package com.msjtrs.ing_app.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyProperty(
   val name : String,
   val catchPhrase: String,
   val bs: String

) :Parcelable {
}