package com.mbn.testwidget.repository

import com.mbn.bitcoinprice.global.App
import io.reactivex.Single

class BitCoinRepository {
    fun executeGetValueOfBitCoinPerOneUSD(currency:String,value:Int): Single<Number> {
        return App.apiServices.getValueOfBitCoinPerOneUSD(currency, value)
    }
}