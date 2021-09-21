package com.mbn.bitcoinprice.view

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import android.util.Log
import com.mbn.bitcoinprice.global.BitcoinData
import com.mbn.bitcoinprice.R


/**
 * Implementation of App Widget functionality.
 */
class BitcoinWidget : AppWidgetProvider() {
    private lateinit var bitcoinData: BitcoinData

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.e("result","onUpdate")

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }


    }
    override fun onReceive(context: Context?, intent: Intent) {
        super.onReceive(context!!, intent)
        Log.e("result",intent.action.toString())
        bitcoinData= BitcoinData(context)
        if (intent.action==AppWidgetManager.ACTION_APPWIDGET_UPDATE){
            Log.e("result","onrecived")
            bitcoinData.fetchData()


        }else if (intent.action=="ManulaClick"){
            Log.e("result","onrecivedManulaClick")
            bitcoinData.fetchData()

        }


        }


    fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ){
        bitcoinData= BitcoinData(context)
        Log.e("result","updateAppWidget")
        bitcoinData.fetchData()

    }


}
