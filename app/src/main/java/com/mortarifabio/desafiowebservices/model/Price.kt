package com.mortarifabio.desafiowebservices.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Price(
    val price: Double,
    val type: String
) : Parcelable