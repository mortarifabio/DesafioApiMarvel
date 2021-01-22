package com.mortarifabio.desafiowebservices.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    val extension: String,
    val path: String
) : Parcelable