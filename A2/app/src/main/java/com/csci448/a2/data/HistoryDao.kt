package com.csci448.a2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

/*
Object for holding the query needed for the program
 */

@Dao
interface HistoryDao {
    //Get all previous games stored in the database
    @Query("SELECT * FROM historydata")
    fun getHistory():LiveData<List<HistoryData>>

    //Get one specific item of history
    @Query("SELECT * FROM historydata WHERE id=(:id)")
    fun getHistoryData(id:UUID):LiveData<HistoryData?>

    //Insert new game
    @Insert
    fun insertGame(historyData:HistoryData)

    @Query("DELETE FROM historydata")
    fun deleteAll()

}