package com.mortarifabio.desafiowebservices.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ComicsResult(
    val dates: List<Date>,
    var description: String?,
    val id: Int,
    val images: List<Image>,
    var issueNumber: String,
    val pageCount: Int,
    val prices: List<Price>,
    val title: String,
    var image: String? = null,
    var zoomImage: String? = null,
    var banner: String? = null,
    var published: String? = null,
    var price: String? = null
) : Parcelable {
    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<ComicsResult> = object : DiffUtil.ItemCallback<ComicsResult>() {
            override fun areItemsTheSame(oldItem: ComicsResult, newItem: ComicsResult): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: ComicsResult, newItem: ComicsResult): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}