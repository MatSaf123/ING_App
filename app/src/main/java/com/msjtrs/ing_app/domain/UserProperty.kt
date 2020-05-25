package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProperty(
    val id: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company

) : Parcelable {

    lateinit var album : AlbumProperty
    //lateinit var photos : List<PhotoProperty>

}