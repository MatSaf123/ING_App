package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class PostProperty(
    val userId: String,
    val id: String,
    val title: String,
    val body: String
) : Parcelable {

    lateinit var posterName : String
    lateinit var posterEmail: String
    lateinit var posterWebsite: String
    lateinit var posterStreet: String
    lateinit var posterCity: String
    lateinit var posterZipcode: String
    lateinit var posterGeoLatitude: String
    lateinit var posterGeoLongitude: String
    var commentCount : Int = 0

    lateinit var comments : List<CommentProperty>



}