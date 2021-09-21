package com.mbn.bitcoinprice.global

import java.text.SimpleDateFormat
import java.util.*



fun getCurrentTime(): String {
    val dateObj: Date = Calendar.getInstance().time
    return SimpleDateFormat("K:mm:ss a", Locale.US).format(dateObj).toString()
}

fun getTimeByTimeStamp(time:Long): String {
    val dateObj = Date(time)
    return SimpleDateFormat("K:mm:ss a", Locale.US).format(dateObj).toString()
}
 fun getDateTime(s: Long): String {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}