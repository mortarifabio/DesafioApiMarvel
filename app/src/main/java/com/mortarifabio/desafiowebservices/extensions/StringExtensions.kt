package com.mortarifabio.desafiowebservices.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.formatDate() : String {
    val dateList = this.take(10).split('-')
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val date = LocalDate.of(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
        date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy").withLocale(Locale.ENGLISH))
    } else {
        "${dateList[1]}/${dateList[2]}/${dateList[0]}"
    }
}