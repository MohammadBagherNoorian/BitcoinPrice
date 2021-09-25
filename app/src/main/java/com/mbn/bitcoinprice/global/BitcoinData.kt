package com.mbn.bitcoinprice.global

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mbn.bitcoinprice.R
import com.mbn.bitcoinprice.db.BitcoinDatabase
import com.mbn.bitcoinprice.model.BTCModel
import com.mbn.bitcoinprice.network.BitCoinService
import com.mbn.bitcoinprice.view.BitcoinWidget
import com.mbn.bitcoinprice.repository.BitCoinRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BitcoinData @Inject constructor( val context: Context,val bitcoinDatabase: BitcoinDatabase) {
    private var result = MutableLiveData<BTCModel>()
    fun getResult(): LiveData<BTCModel> {
        return result
    }

    @Synchronized
    fun fetchData(bitCoinService: BitCoinService) {
        val res: MutableLiveData<Number> = MutableLiveData()
        val disposables = CompositeDisposable()
        val repo = BitCoinRepository(bitCoinService)
        //for show Loading when in progress
        setWidgetViewConfig(
            context,
            context.getString(R.string.loading_text),
            context.getString(R.string.loading_text)
        )
        //for get more Decimal
        disposables.add(
            repo.executeGetValueOfBitCoinPerOneUSD("USD", 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res.value = it
                    Log.e("result1", res.value.toString())
                    convertUsdToBTCPrice(context, res.value.toString())
                }, { t: Throwable? ->
                    convertUsdToBTCPrice(context, "0")

                })
        )

    }

    private fun convertUsdToBTCPrice(context: Context, thousdandUsd: String) {
        //Formula is : BtcPrice=1/thousdandUsd
        if (thousdandUsd != "0") {
            val btcPrice = (1 / thousdandUsd.toDouble()) * 1000
            val btcPricesTwoDecimal = String.format("%.2f", btcPrice)
            result.value = BTCModel(0, btcPricesTwoDecimal).also {
                bitcoinDatabase.provideDao()?.insertBitcoinPrice(it)
            }
            setWidgetViewConfig(
                context, "$btcPricesTwoDecimal$", getTimeByTimeStamp(System.currentTimeMillis())
            )
        } else {
            bitcoinDatabase.provideDao()?.getLastBTCPrice()?.blockingGet().apply {
                if (this != null) {
                    result.value = BTCModel(0, this.price, this.time)
                    setWidgetViewConfig(context, this.price, getTimeByTimeStamp(this.time))
                } else {
                    result.value = BTCModel(0, context.getString(R.string.loading_text))
                    setWidgetViewConfig(
                        context, context.getString(R.string.loading_text), context.getString(
                            R.string.loading_text
                        )
                    )
                }

            }
        }

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setWidgetViewConfig(context: Context, price: String, lastupdate: String) {

        val views = RemoteViews(context.packageName, R.layout.bitcoin)
        views.setTextViewText(R.id.appwidget_text, price)
        views.setTextViewText(R.id.last_update, lastupdate)
        val intent = Intent(context, BitcoinWidget::class.java)
        intent.action = "ManulaClick"
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
        AppWidgetManager.getInstance(context)
            .updateAppWidget(ComponentName(context, BitcoinWidget::class.java), views)
    }
}