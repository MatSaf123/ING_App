package com.msjtrs.ing_app.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentProperty(
    val postId : String,
    val id : String,
    val name : String,
    val email : String,
    val body : String

) : Parcelable {
    var testval : String = "testtest"

}