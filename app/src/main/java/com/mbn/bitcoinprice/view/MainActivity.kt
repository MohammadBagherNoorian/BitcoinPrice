package com.mbn.bitcoinprice.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbn.bitcoinprice.global.BitcoinData
import com.mbn.bitcoinprice.R
import com.mbn.bitcoinprice.adapter.HistoryAdapter
import com.mbn.bitcoinprice.db.BitcoinDatabase
import com.mbn.bitcoinprice.global.getTimeByTimeStamp
import com.mbn.bitcoinprice.network.BitCoinService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var bitCoinService: BitCoinService
    @Inject lateinit var bitcoinData: BitcoinData
    @Inject lateinit var bitcoinDatabase: BitcoinDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bitcoinData.fetchData(bitCoinService)
        bitcoinData.getResult().observe(this, {
            main_price_text.text=it.price
            main_last_update.text= getTimeByTimeStamp(it.time)
            showHistoryofBitcoin()

        })

        refresh.setOnClickListener {
            bitcoinData.fetchData(bitCoinService)
        }

}

    private fun showHistoryofBitcoin(){

        bitcoinDatabase.provideDao().getLast4Average().blockingGet().also {
            val adapter= it?.let { it1 -> HistoryAdapter(it1) }
            val linearlayout = LinearLayoutManager(this)
            history_rv.adapter=adapter
            history_rv.layoutManager=linearlayout

        }
    }

}


