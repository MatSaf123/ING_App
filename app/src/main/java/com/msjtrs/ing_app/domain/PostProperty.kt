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
    lateinit var posterEmail: String
    lateinit var posterAddress: Address
    lateinit var posterPhone: String
    lateinit var posterWebsite: String
    lateinit var posterCompany: Company

}