package com.csci448.a2.data

import java.util.*

data class HistoryData(val id: UUID = UUID.randomUUID(),
                       var winner:String = "",
                       var status:String = "",
                       var date: Date = Date(),
                       var loser:String = "")