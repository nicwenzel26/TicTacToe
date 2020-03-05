package com.csci448.a2.data

import java.util.*

data class HistoryData(val id: UUID = UUID.randomUUID(),
                       var winner:String? = null,
                       var singlePlayer: Boolean = true,
                       var date: Date = Date(),
                       var loser:String? = null,
                       var didTie:Boolean = false)