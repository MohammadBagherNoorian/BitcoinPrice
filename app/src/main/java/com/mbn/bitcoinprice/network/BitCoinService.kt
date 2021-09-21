package com.mbn.bitcoinprice.network
import io.reactivex.Single

import retrofit2.http.*

interface BitCoinService {

    @Headers("content-type:application/x-www-form-urlencoded")
    @GET("tobtc")
    fun getValueOfBitCoinPerOneUSD(
        @Query("currency") currency: String,
        @Query("value") value: Int
    ): Single<Number>


}