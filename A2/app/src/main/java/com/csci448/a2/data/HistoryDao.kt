package com.csci448.a2.data

import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historydata")
    fun getHistory():List<HistoryData>

    @Query("SELECT * FROM historydata WHERE id=(:id)")
    fun getHistoryData(id:UUID):HistoryData?

}