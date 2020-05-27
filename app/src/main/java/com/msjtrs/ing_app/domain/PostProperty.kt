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

    var commentCount : Int = 0
    //lateinit var user : UserProperty
    lateinit var posterName : String
    lateinit var comments : List<CommentProperty>
    lateinit var photos : List<PhotoProperty>



}