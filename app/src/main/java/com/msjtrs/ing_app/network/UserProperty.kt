package com.msjtrs.ing_app.network

import android.os.Parcelable
import com.msjtrs.ing_app.network.data.AddressProperty
import com.msjtrs.ing_app.network.data.CompanyProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProperty(
    val id: String,
    val username: String,
    val email: String,
    val address: AddressProperty,
    val phone: String,
    val website: String,
    val company: CompanyProperty

) : Parcelable {

}