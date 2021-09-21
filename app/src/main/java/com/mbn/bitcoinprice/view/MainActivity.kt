package com.mbn.bitcoinprice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbn.bitcoinprice.global.App
import com.mbn.bitcoinprice.global.BitcoinData
import com.mbn.bitcoinprice.R
import com.mbn.bitcoinprice.adapter.HistoryAdapter
import com.mbn.bitcoinprice.global.getTimeByTimeStamp
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btcDataAdapter= BitcoinData(this)
        btcDataAdapter.fetchData()
        btcDataAdapter.getResult().observe(this, Observer {
            main_price_text.text=it.price
            main_last_update.text= getTimeByTimeStamp(it.time)
            showHistoryofBitcoin()

        })

        refresh.setOnClickListener {
            btcDataAdapter.fetchData()
        }

}

    fun showHistoryofBitcoin(){

        App.appDataBase?.provideDao()?.getLast4Average()?.blockingGet().also {
            val adapter= it?.let { it1 -> HistoryAdapter(it1) }
            val linearlayout = LinearLayoutManager(this)
            history_rv.adapter=adapter
            history_rv.layoutManager=linearlayout

        }
    }

}


