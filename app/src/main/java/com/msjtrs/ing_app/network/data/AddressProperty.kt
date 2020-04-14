package com.msjtrs.ing_app.network.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressProperty(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: GeoProperties
) : Parcelable {
}