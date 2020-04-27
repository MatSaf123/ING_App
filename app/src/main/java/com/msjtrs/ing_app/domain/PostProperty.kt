package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostProperty(
    val userId: String,
    val id: String,
    val title: String,
    val body: String
) : Parcelable {

    lateinit var posterName : String
    var commentCount : Int = 0
}