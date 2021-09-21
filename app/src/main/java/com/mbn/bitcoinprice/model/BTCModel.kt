package com.mbn.bitcoinprice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_price")
data class BTCModel(@PrimaryKey(autoGenerate = true) val id: Long, val price:String, val time: Long = System.currentTimeMillis())