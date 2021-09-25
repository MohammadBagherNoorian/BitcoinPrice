package com.mbn.bitcoinprice.db

import android.content.Context
import android.util.Log
import androidx.room.*
import com.mbn.bitcoinprice.global.getDateTime
import com.mbn.bitcoinprice.model.BTCAverage
import com.mbn.bitcoinprice.model.BTCModel
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class BTCPriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPriceTable(Price: BTCModel): Single<Long>


    @Query("select * from tbl_price")
    abstract fun getBTCPrice(): Maybe<List<BTCModel>>

    @Query("select * from tbl_price order by time DESC LIMIT 1")
    abstract fun getLastBTCPrice(): Maybe<BTCModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAverageTable(average: BTCAverage): Single<Long>


    @Query("select * from tbl_average where date= :time")
    abstract fun getBTCAverageBaseDate(time: String): Maybe<BTCAverage>

    @Query("select * from tbl_average order by id DESC LIMIT 4")
    abstract fun getLast4Average(): Maybe<List<BTCAverage>>

    @Synchronized
    fun insertBitcoinPrice(btcModel: BTCModel) {
        insertPriceTable(btcModel).blockingGet()?.apply {
            Log.e("addedPrice", this.toString())
        }
        getBTCAverageBaseDate(getDateTime(btcModel.time)).blockingGet().apply {
            if (this == null) {
                insertAverageTable(
                    BTCAverage(
                        0, 1, btcModel.price.toDouble(), btcModel.price.toDouble(),
                        getDateTime(btcModel.time)
                    )
                ).blockingGet()?.apply {
                    Log.e("added", this.toString())
                }
            } else {
                var averageModel = this
                averageModel.apply {
                    total++
                    sum += btcModel.price.toDouble()
                    average = sum / total
                }
                insertAverageTable(this).blockingGet().apply {
                    Log.e("update", averageModel.average.toString())
                }
            }
        }
    }
}