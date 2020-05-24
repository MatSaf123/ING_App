package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoProperty(
    val albumId : String,
    val id: String,
    val title: String,
    val thumbnailUrl : String
) : Parcelable {

}