package com.mbn.bitcoinprice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbn.bitcoinprice.model.BTCAverage
import com.mbn.bitcoinprice.model.BTCModel

@Database(entities = [BTCModel::class, BTCAverage::class], version = 2, exportSchema = false)
abstract class BitcoinDatabase : RoomDatabase() {

    abstract fun provideDao(): BTCPriceDao

    companion object {
        fun createDB(context: Context): BitcoinDatabase = Room
            .databaseBuilder(
                context,
                BitcoinDatabase::class.java,
                "bitcoinDB"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

}
