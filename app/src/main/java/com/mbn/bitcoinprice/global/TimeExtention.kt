package com.mbn.bitcoinprice.global

import java.text.SimpleDateFormat
import java.util.*

//get time (hh:mm:ss) by timestamp to show lastupdate
fun getTimeByTimeStamp(time: Long): String {
    val dateObj = Date(time)
    return SimpleDateFormat("K:mm:ss a", Locale.US).format(dateObj).toString()
}

//get date by timestamp (MM/dd/yyyy) To categorize requests by date
fun getDateTime(s: Long): String {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}