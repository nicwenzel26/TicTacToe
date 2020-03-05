package com.csci448.a2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historydata")
    fun getHistory():LiveData<List<HistoryData>>

    @Query("SELECT * FROM historydata WHERE id=(:id)")
    fun getHistoryData(id:UUID):LiveData<HistoryData?>

    @Insert
    fun insertGame(historyData:HistoryData)

}