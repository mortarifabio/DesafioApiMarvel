package com.mortarifabio.desafiowebservices.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ComicsData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicsResult>,
    val total: Int
) : Parcelable