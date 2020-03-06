package com.csci448.a2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

/*
Object for holding the query needed for the program
 */

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historydata")
    fun getHistory():LiveData<List<HistoryData>>

    @Query("SELECT * FROM historydata WHERE id=(:id)")
    fun getHistoryData(id:UUID):LiveData<HistoryData?>

    @Insert
    fun insertGame(historyData:HistoryData)

    @Query("DELETE FROM historydata")
    fun deleteAll()

}