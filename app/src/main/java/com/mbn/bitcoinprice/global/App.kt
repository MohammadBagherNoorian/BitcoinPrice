package com.mbn.bitcoinprice.global

import androidx.multidex.MultiDexApplication
import com.mbn.bitcoinprice.db.BTCPriceDao
import com.mbn.bitcoinprice.network.BitCoinService
import com.mbn.bitcoinprice.network.BitCoinServicesFactory

class App : MultiDexApplication() {
    companion object {
        var appDataBase: BTCPriceDao.BitcoinDatabase?=null
        val apiServices: BitCoinService = BitCoinServicesFactory.makeService(true)
    }

    override fun onCreate() {
        super.onCreate()
        appDataBase = BTCPriceDao.BitcoinDatabase.createDB(this)
    }


}