package com.msjtrs.ing_app.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoProperties(
        val lat: String,
        val lng: String
) : Parcelable {
}