package com.mbn.bitcoinprice

import com.mbn.bitcoinprice.global.getDateTime
import com.mbn.bitcoinprice.global.getTimeByTimeStamp
import org.junit.Test

class TimeExtensionsTest {

    @Test
    fun getDateTimeTest(){
        val date1= getDateTime(System.currentTimeMillis())
        val date2= getDateTime(System.currentTimeMillis())
        assert(date1==date2) { "Test Failed" }
    }
    @Test
    fun getDateTimeTestWithSpeceficTimeStamp(){
        //Thursday, 23 September 2021 12:00:01 PM GMT+03:30
        val date1= getDateTime(1632385801000)
        //Wednesday, 22 September 2021 11:59:59 PM
        val date2= getDateTime(1632342599000)
        assert(date1 != date2) { "Test Failed" }
    }
    @Test
    fun getTimeByTimeStampTest(){
        val time1= getTimeByTimeStamp(1632385801000)
        val time2= getTimeByTimeStamp(1632385801000)
        assert(time1==time2) { "Test Failed" }

    }


}