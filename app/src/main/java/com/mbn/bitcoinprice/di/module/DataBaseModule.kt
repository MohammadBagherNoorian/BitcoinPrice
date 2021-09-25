package com.mbn.bitcoinprice.di.module;

import android.app.Application
import android.content.Context
import com.mbn.bitcoinprice.db.BTCPriceDao
import com.mbn.bitcoinprice.db.BitcoinDatabase

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
 class DataBaseModule {

    @Singleton
    @Provides
     fun provideDatabase(context: Context):BitcoinDatabase{
       return BitcoinDatabase.createDB(context)
     }

}