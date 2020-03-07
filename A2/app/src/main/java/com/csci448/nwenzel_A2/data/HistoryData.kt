package com.csci448.nwenzel_A2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*
Data class to hold the information that will go into the data objects for the history page
UUID for unique database objects
 */
@Entity
data class HistoryData(@PrimaryKey val id: UUID = UUID.randomUUID(),
                       var winner:String? = null,
                       var singlePlayer: Boolean = true,
                       var date: Date = Date(),
                       var loser:String? = null,
                       var didTie:Boolean = false)