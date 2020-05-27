package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumProperty(
    val userId : String,
    val id : String,
    val title: String
) : Parcelable {

    lateinit var photos : List<PhotoProperty>

}