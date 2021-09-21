package com.mbn.bitcoinprice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_average")
data class BTCAverage(@PrimaryKey(autoGenerate = true) var id: Long,var total:Int,var sum:Double,var average:Double,var date:String)