package com.mbn.bitcoinprice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mbn.bitcoinprice.R
import com.mbn.bitcoinprice.model.BTCAverage


class HistoryAdapter(
    private val itemslist: List<BTCAverage>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.historyDate.text= itemslist[position].date
        holder.historyPrice.text=String.format("%.2f",  itemslist[position].average)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return itemslist.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historyDate: AppCompatTextView = itemView.findViewById(R.id.history_date)
        val historyPrice:AppCompatTextView = itemView.findViewById(R.id.history_price)


    }


}