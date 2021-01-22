package com.mortarifabio.desafiowebservices.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Date(
    val date: String,
    val type: String
) : Parcelable