package com.mbn.bitcoinprice.repository

import com.mbn.bitcoinprice.network.BitCoinService
import io.reactivex.Single
import javax.inject.Inject

class BitCoinRepository @Inject constructor(
    private val bitCoinService: BitCoinService){

    fun executeGetValueOfBitCoinPerOneUSD(currency:String,value:Int): Single<Number> {

        return bitCoinService.getValueOfBitCoinPerOneUSD(currency, value)
    }
}